# Оригинальный Mail.ru APK: статическая трассировка PushMe (DEX)

Исходный пакет: ru.mail.mailapp v15.107.0.148045; SHA-256 ZIP: `42c976a0c2f3d5fb186a88bc855f782c81c8ce35f952fcf22f99c64828880af6`.
Исследовано файлов DEX: 21. Никаких запросов к Google/PushMe не было.

Ниже дословные инструкции Dalvik (псевдосинтаксис анализатора),
включая адреса сетевого метода, имена полей и порядок операций.
Отсутствующие детали в самих инструкциях не следует додумывать.


## Lcom/vk/commonid/CommonIdProvider$Companion;::getCommonIdGenerated (Landroid/content/Context; Ljava/lang/Long;)Ljava/lang/String;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes3.dex

~~~smali-like
00000: monitor-enter v2
00002: const-string v0, "context"
00006: invoke-static v3, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000c: invoke-static Lcom/vk/commonid/CommonIdProvider;->access$getCommonIdProvider$cp()Lcom/vk/commonid/CommonIdProvider;
00012: move-result-object v0
00014: if-nez v0, +00dh
00018: new-instance v0, Lcom/vk/commonid/CommonIdPrefs;
0001c: invoke-direct v0, v3, Lcom/vk/commonid/CommonIdPrefs;-><init>(Landroid/content/Context;)V
00022: invoke-static v2, v0, Lcom/vk/commonid/CommonIdProvider$Companion;->lpmidinommockvmoca(Lcom/vk/commonid/CommonIdProvider$Companion; Lcom/vk/commonid/CommonIdPrefs;)V
00028: goto +3h
0002a: move-exception v3
0002c: goto +14h
0002e: invoke-static Lcom/vk/commonid/CommonIdProvider;->access$getCommonIdProvider$cp()Lcom/vk/commonid/CommonIdProvider;
00034: move-result-object v0
00036: if-nez v0, +008h
0003a: const-string v0, "commonIdProvider"
0003e: invoke-static v0, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V
00044: const/4 v0, 0
00046: const/4 v1, 0
00048: invoke-static v0, v3, v1, v4, Lcom/vk/commonid/CommonIdProvider;->access$getOrGenerateCommonIdInternal(Lcom/vk/commonid/CommonIdProvider; Landroid/content/Context; Z Ljava/lang/Long;)Ljava/lang/String;
0004e: move-result-object v3
00050: monitor-exit v2
00052: return-object v3
00054: monitor-exit v2
00056: throw v3
~~~


## Lcom/vk/commonid/CommonIdProvider$Companion;::getCommonIdGenerated$default (Lcom/vk/commonid/CommonIdProvider$Companion; Landroid/content/Context; Ljava/lang/Long; I Ljava/lang/Object;)Ljava/lang/String;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes3.dex

~~~smali-like
00000: and-int/lit8 v3, v3, 2
00004: if-eqz v3, +003h
00008: const/4 v2, 0
0000a: invoke-virtual v0, v1, v2, Lcom/vk/commonid/CommonIdProvider$Companion;->getCommonIdGenerated(Landroid/content/Context; Ljava/lang/Long;)Ljava/lang/String;
00010: move-result-object v0
00012: return-object v0
~~~


## Lcom/vk/commonid/CommonIdProvider;::getCommonIdGenerated (Landroid/content/Context; Ljava/lang/Long;)Ljava/lang/String;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes3.dex

~~~smali-like
00000: const-class v0, Lcom/vk/commonid/CommonIdProvider;
00004: monitor-enter v0
00006: sget-object v1, Lcom/vk/commonid/CommonIdProvider;->Companion Lcom/vk/commonid/CommonIdProvider$Companion;
0000a: invoke-virtual v1, v2, v3, Lcom/vk/commonid/CommonIdProvider$Companion;->getCommonIdGenerated(Landroid/content/Context; Ljava/lang/Long;)Ljava/lang/String;
00010: move-result-object v2
00012: monitor-exit v0
00014: return-object v2
00016: move-exception v2
00018: monitor-exit v0
0001a: throw v2
~~~


## Lcom/vk/pushme/logic/SubscriptionBatcher;::batch (Ljava/util/Collection;)Ljava/util/Collection;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes19.dex

~~~smali-like
00000: const-string v0, "input"
00004: invoke-static v9, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000a: invoke-interface v9, Ljava/util/Collection;->isEmpty()Z
00010: move-result v0
00012: if-eqz v0, +009h
00016: invoke-static Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;
0001c: move-result-object v9
0001e: check-cast v9, Ljava/util/Collection;
00022: return-object v9
00024: check-cast v9, Ljava/lang/Iterable;
00028: new-instance v0, Ljava/util/LinkedHashMap;
0002c: invoke-direct v0, Ljava/util/LinkedHashMap;-><init>()V
00032: invoke-interface v9, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00038: move-result-object v9
0003a: invoke-interface v9, Ljava/util/Iterator;->hasNext()Z
00040: move-result v1
00042: if-eqz v1, +021h
00046: invoke-interface v9, Ljava/util/Iterator;->next()Ljava/lang/Object;
0004c: move-result-object v1
0004e: move-object v2, v1
00050: check-cast v2, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
00054: invoke-virtual v2, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getApplication()Ljava/lang/String;
0005a: move-result-object v2
0005c: invoke-interface v0, v2, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;
00062: move-result-object v3
00064: if-nez v3, +00ah
00068: new-instance v3, Ljava/util/ArrayList;
0006c: invoke-direct v3, Ljava/util/ArrayList;-><init>()V
00072: invoke-interface v0, v2, v3, Ljava/util/Map;->put(Ljava/lang/Object; Ljava/lang/Object;)Ljava/lang/Object;
00078: check-cast v3, Ljava/util/List;
0007c: invoke-interface v3, v1, Ljava/util/List;->add(Ljava/lang/Object;)Z
00082: goto -24h
00084: new-instance v9, Ljava/util/ArrayList;
00088: invoke-direct v9, Ljava/util/ArrayList;-><init>()V
0008e: new-instance v1, Ljava/util/ArrayList;
00092: invoke-direct v1, Ljava/util/ArrayList;-><init>()V
00098: invoke-interface v0, Ljava/util/Map;->values()Ljava/util/Collection;
0009e: move-result-object v0
000a0: invoke-interface v0, Ljava/util/Collection;->iterator()Ljava/util/Iterator;
000a6: move-result-object v0
000a8: const/4 v2, 0
000aa: move v3, v2
000ac: invoke-interface v0, Ljava/util/Iterator;->hasNext()Z
000b2: move-result v4
000b4: if-eqz v4, +022h
000b8: invoke-interface v0, Ljava/util/Iterator;->next()Ljava/lang/Object;
000be: move-result-object v4
000c0: check-cast v4, Ljava/util/List;
000c4: invoke-interface v4, Ljava/util/List;->size()I
000ca: move-result v5
000cc: add-int v6, v3, v5
000d0: iget v7, v8, Lcom/vk/pushme/logic/SubscriptionBatcher;->limit I
000d4: if-le v6, v7, +00bh
000d8: invoke-interface v9, v1, Ljava/util/List;->add(Ljava/lang/Object;)Z
000de: new-instance v1, Ljava/util/ArrayList;
000e2: invoke-direct v1, Ljava/util/ArrayList;-><init>()V
000e8: move v3, v2
000ea: check-cast v4, Ljava/util/Collection;
000ee: invoke-interface v1, v4, Ljava/util/List;->addAll(Ljava/util/Collection;)Z
000f4: add-int/2addr v3, v5
000f6: goto -25h
000f8: invoke-interface v9, v1, Ljava/util/List;->add(Ljava/lang/Object;)Z
000fe: return-object v9
~~~


## Lcom/vk/pushme/logic/request/UnsubscribeRequest;::enqueue ()V
Source APK member: Mail-15.107.0.148045.apk; DEX: classes19.dex

~~~smali-like
00000: iget-object v0, v10, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
00004: iget-object v1, v10, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->application Ljava/lang/String;
00008: iget-object v2, v10, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->accountForUnsubscribe Ljava/lang/String;
0000c: new-instance v3, Ljava/lang/StringBuilder;
00010: invoke-direct v3, Ljava/lang/StringBuilder;-><init>()V
00016: const-string v4, "Calling enqueue for application "
0001a: invoke-virtual v3, v4, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00020: invoke-virtual v3, v1, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00026: const-string v1, ", account = "
0002a: invoke-virtual v3, v1, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00030: invoke-virtual v3, v2, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00036: invoke-virtual v3, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
0003c: move-result-object v1
0003e: const/4 v2, 2
00040: const/4 v3, 0
00042: invoke-static v0, v1, v3, v2, v3, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00048: iget-object v4, v10, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->coroutineScope Lkotlinx/coroutines/CoroutineScope;
0004c: new-instance v7, Lcom/vk/pushme/logic/request/UnsubscribeRequest$enqueue$1;
00050: invoke-direct v7, v10, v3, Lcom/vk/pushme/logic/request/UnsubscribeRequest$enqueue$1;-><init>(Lcom/vk/pushme/logic/request/UnsubscribeRequest; Lkotlin/coroutines/Continuation;)V
00056: const/4 v8, 3
00058: const/4 v9, 0
0005a: const/4 v5, 0
0005c: const/4 v6, 0
0005e: invoke-static/range v4 ... v9, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope; Lkotlin/coroutines/CoroutineContext; Lkotlinx/coroutines/CoroutineStart; Lkotlin/jvm/functions/Function2; I Ljava/lang/Object;)Lkotlinx/coroutines/Job;
00064: return-void 
~~~


## Lcom/vk/pushme/logic/request/UnsubscribeRequest;::execute ()Lcom/vk/pushme/model/result/UnsubscribeResult;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes19.dex

~~~smali-like
00000: sget-object v0, Lcom/vk/pushme/util/Utils;->INSTANCE Lcom/vk/pushme/util/Utils;
00004: invoke-virtual v0, Lcom/vk/pushme/util/Utils;->checkNotMainThread()V
0000a: iget-object v0, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
0000e: iget-object v1, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->application Ljava/lang/String;
00012: iget-object v2, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->accountForUnsubscribe Ljava/lang/String;
00016: new-instance v3, Ljava/lang/StringBuilder;
0001a: invoke-direct v3, Ljava/lang/StringBuilder;-><init>()V
00020: const-string v4, "Calling execute for application "
00024: invoke-virtual v3, v4, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0002a: invoke-virtual v3, v1, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00030: const-string v1, ", account = "
00034: invoke-virtual v3, v1, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0003a: invoke-virtual v3, v2, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00040: invoke-virtual v3, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00046: move-result-object v1
00048: const/4 v2, 0
0004a: const/4 v3, 2
0004c: invoke-static v0, v1, v2, v3, v2, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00052: new-instance v0, Lcom/vk/pushme/logic/request/UnsubscribeRequest$execute$accounts$1;
00056: invoke-direct v0, v8, v2, Lcom/vk/pushme/logic/request/UnsubscribeRequest$execute$accounts$1;-><init>(Lcom/vk/pushme/logic/request/UnsubscribeRequest; Lkotlin/coroutines/Continuation;)V
0005c: const/4 v1, 1
0005e: invoke-static v2, v0, v1, v2, Lkotlinx/coroutines/BuildersKt;->runBlocking$default(Lkotlin/coroutines/CoroutineContext; Lkotlin/jvm/functions/Function2; I Ljava/lang/Object;)Ljava/lang/Object;
00064: move-result-object v0
00066: check-cast v0, Ljava/util/Set;
0006a: iget-object v4, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
0006e: invoke-interface v0, Ljava/util/Set;->size()I
00074: move-result v5
00076: new-instance v6, Ljava/lang/StringBuilder;
0007a: invoke-direct v6, Ljava/lang/StringBuilder;-><init>()V
00080: const-string v7, "Found "
00084: invoke-virtual v6, v7, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0008a: invoke-virtual v6, v5, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00090: const-string v5, " for unsubscribe"
00094: invoke-virtual v6, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0009a: invoke-virtual v6, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
000a0: move-result-object v5
000a2: invoke-static v4, v5, v2, v3, v2, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
000a8: new-instance v4, Lcom/vk/pushme/logic/request/UnsubscribeRequest$execute$networkResult$1;
000ac: invoke-direct v4, v8, v0, v2, Lcom/vk/pushme/logic/request/UnsubscribeRequest$execute$networkResult$1;-><init>(Lcom/vk/pushme/logic/request/UnsubscribeRequest; Ljava/util/Set; Lkotlin/coroutines/Continuation;)V
000b2: invoke-static v2, v4, v1, v2, Lkotlinx/coroutines/BuildersKt;->runBlocking$default(Lkotlin/coroutines/CoroutineContext; Lkotlin/jvm/functions/Function2; I Ljava/lang/Object;)Ljava/lang/Object;
000b8: move-result-object v4
000ba: check-cast v4, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result;
000be: sget-object v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$OK;->INSTANCE Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$OK;
000c2: invoke-static v4, v5, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object; Ljava/lang/Object;)Z
000c8: move-result v5
000ca: if-eqz v5, +033h
000ce: iget-object v4, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
000d2: const-string v5, "Unsubscribe from server successfully completed"
000d6: invoke-static v4, v5, v2, v3, v2, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
000dc: new-instance v4, Lcom/vk/pushme/logic/request/UnsubscribeRequest$execute$deleted$1;
000e0: invoke-direct v4, v8, v0, v2, Lcom/vk/pushme/logic/request/UnsubscribeRequest$execute$deleted$1;-><init>(Lcom/vk/pushme/logic/request/UnsubscribeRequest; Ljava/util/Set; Lkotlin/coroutines/Continuation;)V
000e6: invoke-static v2, v4, v1, v2, Lkotlinx/coroutines/BuildersKt;->runBlocking$default(Lkotlin/coroutines/CoroutineContext; Lkotlin/jvm/functions/Function2; I Ljava/lang/Object;)Ljava/lang/Object;
000ec: move-result-object v0
000ee: check-cast v0, Ljava/lang/Number;
000f2: invoke-virtual v0, Ljava/lang/Number;->intValue()I
000f8: move-result v0
000fa: iget-object v1, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
000fe: new-instance v4, Ljava/lang/StringBuilder;
00102: invoke-direct v4, Ljava/lang/StringBuilder;-><init>()V
00108: invoke-virtual v4, v0, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
0010e: const-string v0, " local entities have been deleted"
00112: invoke-virtual v4, v0, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00118: invoke-virtual v4, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
0011e: move-result-object v0
00120: invoke-static v1, v0, v2, v3, v2, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00126: sget-object v0, Lcom/vk/pushme/model/result/UnsubscribeResult$OK;->INSTANCE Lcom/vk/pushme/model/result/UnsubscribeResult$OK;
0012a: return-object v0
0012c: move-exception v0
0012e: goto +3fh
00130: sget-object v0, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$MissingDeviceIdError;->INSTANCE Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$MissingDeviceIdError;
00134: invoke-static v4, v0, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object; Ljava/lang/Object;)Z
0013a: move-result v0
0013c: if-eqz v0, +014h
00140: const-string v0, "Unable to get device ID"
00144: iget-object v1, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
00148: invoke-static v1, v0, v2, v3, v2, Lcom/vk/pushme/common/Logger;->error$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
0014e: new-instance v1, Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;
00152: new-instance v2, Ljava/lang/IllegalStateException;
00156: invoke-direct v2, v0, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
0015c: invoke-direct v1, v2, Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;-><init>(Ljava/lang/Throwable;)V
00162: return-object v1
00164: instance-of v0, v4, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;
00168: if-eqz v0, +01ch
0016c: iget-object v0, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
00170: const-string v1, "Failed to unsubscribe"
00174: move-object v2, v4
00176: check-cast v2, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;
0017a: invoke-virtual v2, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;->getT()Ljava/lang/Throwable;
00180: move-result-object v2
00182: invoke-interface v0, v1, v2, Lcom/vk/pushme/common/Logger;->error(Ljava/lang/String; Ljava/lang/Throwable;)V
00188: new-instance v0, Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;
0018c: check-cast v4, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;
00190: invoke-virtual v4, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;->getT()Ljava/lang/Throwable;
00196: move-result-object v1
00198: invoke-direct v0, v1, Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;-><init>(Ljava/lang/Throwable;)V
0019e: return-object v0
001a0: new-instance v0, Lkotlin/NoWhenBranchMatchedException;
001a4: invoke-direct v0, Lkotlin/NoWhenBranchMatchedException;-><init>()V
001aa: throw v0
001ac: iget-object v1, v8, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->logger Lcom/vk/pushme/common/Logger;
001b0: const-string v2, "Unable to unsubscribe due to exception"
001b4: invoke-interface v1, v2, v0, Lcom/vk/pushme/common/Logger;->error(Ljava/lang/String; Ljava/lang/Throwable;)V
001ba: new-instance v1, Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;
001be: invoke-direct v1, v0, Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;-><init>(Ljava/lang/Throwable;)V
001c4: return-object v1
~~~

## Lcom/vk/pushme/logic/request/UnsubscribeRequest;::execute ()Ljava/lang/Object;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes19.dex

~~~smali-like
00000: invoke-virtual v1, Lcom/vk/pushme/logic/request/UnsubscribeRequest;->execute()Lcom/vk/pushme/model/result/UnsubscribeResult;
00006: move-result-object v0
00008: return-object v0
~~~


## Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;::invoke (Ljava/util/Collection; Z Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes19.dex

~~~smali-like
00000: move-object/from16 v1, v33
00004: move-object/from16 v0, v34
00008: move-object/from16 v2, v36
0000c: const-string v9, "Api result V2: invalid accounts ("
00010: const-string v10, "Api result V1: invalid accounts ("
00014: instance-of v3, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;
00018: if-eqz v3, +012h
0001c: move-object v3, v2
0001e: check-cast v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;
00022: iget v4, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00026: const/high16 v5, -2147483648
0002a: and-int v6, v4, v5
0002e: if-eqz v6, +007h
00032: sub-int/2addr v4, v5
00034: iput v4, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00038: move-object v11, v3
0003a: goto +7h
0003c: new-instance v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;
00040: invoke-direct v3, v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;-><init>(Lcom/vk/pushme/logic/usecase/SubscriptionUseCase; Lkotlin/coroutines/Continuation;)V
00046: goto -7h
00048: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->result Ljava/lang/Object;
0004c: invoke-static Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;
00052: move-result-object v12
00054: iget v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00058: const-string v13, "INVALID_ACCOUNT_ERROR"
0005c: const-string v14, ")"
00060: const/4 v4, 3
00062: const/4 v7, 2
00064: const/4 v8, 1
00066: if-eqz v3, +161h
0006a: if-eq v3, v8, +14dh
0006e: if-eq v3, v7, +133h
00072: if-eq v3, v4, +0a1h
00076: const/4 v0, 4
00078: if-ne v3, v0, +096h
0007c: iget v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
00080: iget-boolean v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
00084: iget-object v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
00088: check-cast v4, Ljava/util/Set;
0008c: iget-object v10, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
00090: check-cast v10, Ljava/util/Collection;
00094: iget-object v10, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
00098: check-cast v10, Ljava/util/Iterator;
0009c: iget-object v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
000a0: check-cast v5, Ljava/lang/Iterable;
000a4: iget-object v6, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
000a8: check-cast v6, Lcom/vk/pushme/network/PushMeApi;
000ac: iget-object v8, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
000b0: check-cast v8, Ljava/util/Set;
000b4: iget-object v7, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
000b8: check-cast v7, Ljava/util/Set;
000bc: iget-object v15, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
000c0: check-cast v15, Lcom/vk/pushme/logic/SubscriptionBatcher;
000c4: move-object/from16 v19, v2
000c8: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
000cc: check-cast v2, Ljava/lang/String;
000d0: move-object/from16 v34, v2
000d4: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
000d8: check-cast v2, Ljava/lang/String;
000dc: move-object/from16 v35, v2
000e0: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
000e4: check-cast v2, Ljava/util/List;
000e8: move-object/from16 v20, v2
000ec: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
000f0: check-cast v2, Ljava/util/List;
000f4: move-object/from16 v21, v2
000f8: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
000fc: check-cast v2, Ljava/util/Collection;
00100: move-object/from16 v22, v2
00104: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00108: check-cast v2, Ljava/lang/String;
0010c: move-object/from16 v23, v2
00110: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00114: check-cast v2, Ljava/lang/String;
00118: move-object/from16 v24, v2
0011c: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
00120: check-cast v2, Ljava/util/List;
00124: move-object/from16 v25, v2
00128: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
0012c: check-cast v2, Ljava/lang/String;
00130: move-object/from16 v26, v2
00134: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00138: check-cast v2, Ljava/util/Collection;
0013c: invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00142: move-object v1, v8
00144: move v8, v3
00146: move-object/from16 v3, v26
0014a: move-object/from16 v26, v13
0014e: move-object v13, v1
00150: move-object/from16 v16, v10
00154: move-object/from16 v27, v14
00158: move-object/from16 v17, v15
0015c: move-object/from16 v30, v22
00160: move-object/from16 v18, v25
00164: const/4 v1, 0
00166: const/16 v10, 10
0016a: const/4 v14, 4
0016c: move-object/from16 v25, v5
00170: move-object v15, v7
00172: move-object/from16 v22, v9
00176: move-object/from16 v5, v35
0017a: move v9, v0
0017c: move-object v0, v2
0017e: move-object v7, v6
00180: move-object/from16 v2, v19
00184: move-object/from16 v19, v23
00188: move-object/from16 v6, v34
0018c: goto/16 +58dh
00190: move-exception v0
00192: const/4 v4, 1
00194: const/4 v5, 0
00196: goto/16 +6f3h
0019a: move-exception v0
0019c: move-object/from16 v2, v26
001a0: goto/16 +6e2h
001a4: new-instance v0, Ljava/lang/IllegalStateException;
001a8: const-string v2, "call to 'resume' before 'invoke' with coroutine"
001ac: invoke-direct v0, v2, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
001b2: throw v0
001b4: move-object/from16 v19, v2
001b8: iget v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
001bc: iget-boolean v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
001c0: iget-object v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
001c4: check-cast v3, Ljava/util/Set;
001c8: iget-object v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
001cc: check-cast v5, Ljava/util/Collection;
001d0: iget-object v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
001d4: check-cast v5, Ljava/util/Iterator;
001d8: iget-object v6, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
001dc: check-cast v6, Ljava/lang/Iterable;
001e0: iget-object v7, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
001e4: check-cast v7, Lcom/vk/pushme/network/PushMeApi;
001e8: iget-object v8, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
001ec: check-cast v8, Ljava/util/Set;
001f0: iget-object v15, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
001f4: check-cast v15, Ljava/util/Set;
001f8: iget-object v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
001fc: check-cast v4, Lcom/vk/pushme/logic/SubscriptionBatcher;
00200: move/from16 v21, v2
00204: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
00208: check-cast v2, Ljava/lang/String;
0020c: move-object/from16 v34, v2
00210: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
00214: check-cast v2, Ljava/lang/String;
00218: move-object/from16 v35, v2
0021c: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
00220: check-cast v2, Ljava/util/List;
00224: move-object/from16 v22, v2
00228: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
0022c: check-cast v2, Ljava/util/List;
00230: move-object/from16 v23, v2
00234: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
00238: check-cast v2, Ljava/util/Collection;
0023c: move-object/from16 v24, v2
00240: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00244: check-cast v2, Ljava/lang/String;
00248: move-object/from16 v25, v2
0024c: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00250: check-cast v2, Ljava/lang/String;
00254: move-object/from16 v26, v2
00258: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
0025c: check-cast v2, Ljava/util/List;
00260: move-object/from16 v27, v2
00264: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
00268: check-cast v2, Ljava/lang/String;
0026c: move-object/from16 v28, v2
00270: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00274: check-cast v2, Ljava/util/Collection;
00278: invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
0027e: move-object/from16 v17, v6
00282: move-object/from16 v1, v22
00286: move-object/from16 v30, v24
0028a: move-object/from16 v20, v26
0028e: move-object/from16 v18, v27
00292: move v6, v0
00294: move-object v0, v2
00296: move-object/from16 v22, v9
0029a: move-object/from16 v26, v10
0029e: move-object/from16 v2, v19
002a2: move-object/from16 v19, v25
002a6: move-object v10, v4
002a8: move-object v9, v8
002aa: move-object/from16 v25, v14
002ae: move-object/from16 v14, v23
002b2: move-object/from16 v8, v34
002b6: move-object v4, v3
002b8: move-object/from16 v23, v13
002bc: move-object v13, v15
002be: move-object/from16 v15, v28
002c2: move-object/from16 v3, v35
002c6: goto/16 +341h
002ca: move-exception v0
002cc: move-object/from16 v2, v28
002d0: goto/16 +64ah
002d4: move-object/from16 v19, v2
002d8: iget-boolean v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
002dc: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
002e0: check-cast v2, Ljava/lang/String;
002e4: iget-object v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
002e8: check-cast v3, Ljava/util/Collection;
002ec: invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
002f2: move-object v15, v2
002f4: move-object/from16 v2, v19
002f8: const/4 v5, 2
002fa: goto/16 +086h
002fe: move-exception v0
00300: goto/16 +632h
00304: move-object/from16 v19, v2
00308: iget-boolean v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
0030c: iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
00310: check-cast v2, Ljava/lang/String;
00314: iget-object v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00318: check-cast v3, Ljava/util/Collection;
0031c: invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00322: move v4, v0
00324: move-object v0, v3
00326: goto +59h
00328: move-object/from16 v19, v2
0032c: invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00332: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00336: invoke-interface v0, Ljava/util/Collection;->size()I
0033c: move-result v3
0033e: new-instance v4, Ljava/lang/StringBuilder;
00342: invoke-direct v4, Ljava/lang/StringBuilder;-><init>()V
00348: const-string v5, "UseCase started, subscriptions count: "
0034c: invoke-virtual v4, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00352: invoke-virtual v4, v3, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00358: invoke-virtual v4, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
0035e: move-result-object v3
00360: const/4 v4, 2
00362: const/4 v5, 0
00364: invoke-static v2, v3, v5, v4, v5, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
0036a: invoke-interface v0, Ljava/util/Collection;->isEmpty()Z
00370: move-result v2
00372: if-nez v2, +60bh
00376: move-object/from16 v21, v0
0037a: check-cast v21, Ljava/lang/Iterable;
0037e: new-instance v27, Lcom/vk/pushme/logic/usecase/d;
00382: invoke-direct/range v27, Lcom/vk/pushme/logic/usecase/d;-><init>()V
00388: const/16 v28, 31
0038c: const/16 v29, 0
00390: const/16 v22, 0
00394: const/16 v23, 0
00398: const/16 v24, 0
0039c: const/16 v25, 0
003a0: const/16 v26, 0
003a4: invoke-static/range v21 ... v29, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
003aa: move-result-object v2
003ac: iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
003b0: iput-object v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
003b4: iput-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
003b8: move/from16 v4, v35
003bc: iput-boolean v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
003c0: const/4 v5, 1
003c2: iput v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
003c6: const/4 v6, 0
003c8: invoke-static v3, v6, v11, v5, v6, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->lock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; Lkotlin/coroutines/Continuation; I Ljava/lang/Object;)Ljava/lang/Object;
003ce: move-result-object v3
003d0: if-ne v3, v12, +004h
003d4: goto/16 +45bh
003d8: iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->pushTokenDao Lcom/vk/pushme/database/dao/PushTokenDao;
003dc: iput-object v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
003e0: iput-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
003e4: iput-boolean v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
003e8: const/4 v5, 2
003ea: iput v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
003ee: invoke-virtual v3, v11, Lcom/vk/pushme/database/dao/PushTokenDao;->getAll(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
003f4: move-result-object v3
003f6: if-ne v3, v12, +004h
003fa: goto/16 +448h
003fe: move-object v15, v2
00400: move-object v2, v3
00402: move-object v3, v0
00404: move v0, v4
00406: move-object/from16 v18, v2
0040a: check-cast v18, Ljava/util/List;
0040e: invoke-interface/range v18, Ljava/util/List;->isEmpty()Z
00414: move-result v2
00416: if-eqz v2, +013h
0041a: invoke-direct v1, v15, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handlePushTokensEmpty(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00420: move-result-object v0
00422: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00426: const/4 v4, 1
00428: const/4 v5, 0
0042a: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00430: return-object v0
00432: move-exception v0
00434: const/4 v4, 1
00436: move-object v2, v15
00438: goto/16 +596h
0043c: const/4 v4, 1
0043e: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->deviceIdProvider Lcom/vk/pushme/util/provider/DeviceIdProvider;
00442: invoke-interface v2, Lcom/vk/pushme/util/provider/DeviceIdProvider;->getDeviceId()Ljava/lang/String;
00448: move-result-object v2
0044a: if-eqz v2, +581h
0044e: invoke-static v2, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
00454: move-result v6
00456: if-eqz v6, +004h
0045a: goto/16 +579h
0045e: iget-object v6, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->deviceIdProvider Lcom/vk/pushme/util/provider/DeviceIdProvider;
00462: invoke-interface v6, Lcom/vk/pushme/util/provider/DeviceIdProvider;->getAndroidId()Ljava/lang/String;
00468: move-result-object v6
0046a: if-eqz v6, +565h
0046e: invoke-static v6, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
00474: move-result v7
00476: if-eqz v7, +004h
0047a: goto/16 +55dh
0047e: move v7, v5
00480: move-object v5, v2
00482: move-object v2, v3
00484: move-object/from16 v3, v18
00488: check-cast v3, Ljava/util/Collection;
0048c: if-eqz v0, +012h
00490: iget-object v8, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->deviceIdProvider Lcom/vk/pushme/util/provider/DeviceIdProvider;
00494: invoke-interface v8, Lcom/vk/pushme/util/provider/DeviceIdProvider;->getSdkDeviceId()Ljava/lang/String;
0049a: move-result-object v8
0049c: move/from16 v17, v4
004a0: move-object v4, v6
004a2: move-object v6, v8
004a4: goto +ah
004a6: move-exception v0
004a8: goto/16 -18ah
004ac: move-exception v0
004ae: goto -3ch
004b0: move/from16 v17, v4
004b4: move-object v4, v6
004b6: const/4 v6, 0
004b8: iget-object v8, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->clientInfoProvider Lcom/vk/pushme/util/provider/ClientInfoProvider;
004bc: invoke-interface v8, Lcom/vk/pushme/util/provider/ClientInfoProvider;->getClientInfo()Ljava/util/Map;
004c2: move-result-object v8
004c4: iget-object v7, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->clientInfoProvider Lcom/vk/pushme/util/provider/ClientInfoProvider;
004c8: invoke-interface v7, Lcom/vk/pushme/util/provider/ClientInfoProvider;->getClientTimeZone()Ljava/lang/String;
004ce: move-result-object v7
004d0: move-object/from16 v19, v8
004d4: move-object v8, v7
004d6: move-object/from16 v7, v19
004da: move-object/from16 v19, v11
004de: move/from16 v11, v17
004e2: invoke-direct/range v1 ... v8, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mapToNetworkModels(Ljava/util/Collection; Ljava/util/Collection; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/util/Map; Ljava/lang/String;)Ljava/util/Collection;
004e8: move-result-object v3
004ea: invoke-interface v3, Ljava/util/Collection;->isEmpty()Z
004f0: move-result v6
004f2: if-eqz v6, +011h
004f6: invoke-direct v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoSubscriptionsFound()Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
004fc: move-result-object v0
004fe: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00502: const/4 v5, 0
00504: invoke-static v2, v5, v11, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
0050a: return-object v0
0050c: move-exception v0
0050e: move v4, v11
00510: goto/16 -1beh
00514: move-object v6, v3
00516: check-cast v6, Ljava/lang/Iterable;
0051a: new-instance v7, Ljava/util/ArrayList;
0051e: invoke-direct v7, Ljava/util/ArrayList;-><init>()V
00524: invoke-interface v6, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
0052a: move-result-object v6
0052c: invoke-interface v6, Ljava/util/Iterator;->hasNext()Z
00532: move-result v8
00534: if-eqz v8, +019h
00538: invoke-interface v6, Ljava/util/Iterator;->next()Ljava/lang/Object;
0053e: move-result-object v8
00540: move-object/from16 v17, v8
00544: check-cast v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
00548: invoke-virtual/range v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getApplication()Ljava/lang/String;
0054e: move-result-object v11
00550: invoke-direct v1, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->isV1(Ljava/lang/String;)Z
00556: move-result v11
00558: if-eqz v11, +005h
0055c: invoke-interface v7, v8, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
00562: const/4 v11, 1
00564: goto -1ch
00566: move-object v6, v3
00568: check-cast v6, Ljava/lang/Iterable;
0056c: new-instance v8, Ljava/util/ArrayList;
00570: invoke-direct v8, Ljava/util/ArrayList;-><init>()V
00576: invoke-interface v6, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
0057c: move-result-object v6
0057e: invoke-interface v6, Ljava/util/Iterator;->hasNext()Z
00584: move-result v11
00586: if-eqz v11, +01ch
0058a: invoke-interface v6, Ljava/util/Iterator;->next()Ljava/lang/Object;
00590: move-result-object v11
00592: move-object/from16 v17, v11
00596: check-cast v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
0059a: move-object/from16 v35, v2
0059e: invoke-virtual/range v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getApplication()Ljava/lang/String;
005a4: move-result-object v2
005a6: invoke-direct v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->isV1(Ljava/lang/String;)Z
005ac: move-result v2
005ae: if-nez v2, +005h
005b2: invoke-interface v8, v11, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
005b8: move-object/from16 v2, v35
005bc: goto -1fh
005be: move-object/from16 v35, v2
005c2: new-instance v26, Lcom/vk/pushme/logic/usecase/e;
005c6: invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/e;-><init>()V
005cc: const/16 v27, 31
005d0: const/16 v28, 0
005d4: const/16 v21, 0
005d8: const/16 v22, 0
005dc: const/16 v23, 0
005e0: const/16 v24, 0
005e4: const/16 v25, 0
005e8: move-object/from16 v20, v7
005ec: invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
005f2: move-result-object v2
005f4: new-instance v26, Lcom/vk/pushme/logic/usecase/f;
005f8: invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/f;-><init>()V
005fe: const/16 v27, 31
00602: const/16 v28, 0
00606: const/16 v21, 0
0060a: const/16 v22, 0
0060e: const/16 v23, 0
00612: const/16 v24, 0
00616: const/16 v25, 0
0061a: invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
00620: move-result-object v6
00622: move-object/from16 v7, v20
00626: iget-object v11, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
0062a: move-object/from16 v17, v2
0062e: new-instance v2, Ljava/lang/StringBuilder;
00632: invoke-direct v2, Ljava/lang/StringBuilder;-><init>()V
00638: move-object/from16 v30, v3
0063c: const-string v3, "Apps in v1 request: "
00640: invoke-virtual v2, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00646: invoke-virtual v2, v6, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0064c: invoke-virtual v2, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00652: move-result-object v2
00654: const/4 v3, 2
00656: const/4 v6, 0
00658: invoke-static v11, v2, v6, v3, v6, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
0065e: new-instance v26, Lcom/vk/pushme/logic/usecase/g;
00662: invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/g;-><init>()V
00668: const/16 v27, 31
0066c: const/16 v28, 0
00670: const/16 v21, 0
00674: const/16 v22, 0
00678: const/16 v23, 0
0067c: const/16 v24, 0
00680: const/16 v25, 0
00684: move-object/from16 v20, v8
00688: invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
0068e: move-result-object v2
00690: new-instance v26, Lcom/vk/pushme/logic/usecase/h;
00694: invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/h;-><init>()V
0069a: const/16 v27, 31
0069e: const/16 v28, 0
006a2: const/16 v21, 0
006a6: const/16 v22, 0
006aa: const/16 v23, 0
006ae: const/16 v24, 0
006b2: const/16 v25, 0
006b6: invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
006bc: move-result-object v3
006be: iget-object v6, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
006c2: new-instance v8, Ljava/lang/StringBuilder;
006c6: invoke-direct v8, Ljava/lang/StringBuilder;-><init>()V
006cc: const-string v11, "Apps in v2 request: "
006d0: invoke-virtual v8, v11, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
006d6: invoke-virtual v8, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
006dc: invoke-virtual v8, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
006e2: move-result-object v3
006e4: const/4 v8, 2
006e6: const/4 v11, 0
006e8: invoke-static v6, v3, v11, v8, v11, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
006ee: new-instance v3, Lcom/vk/pushme/logic/SubscriptionBatcher;
006f2: const/16 v6, 20
006f6: invoke-direct v3, v6, Lcom/vk/pushme/logic/SubscriptionBatcher;-><init>(I)V
006fc: move-object/from16 v6, v35
00700: check-cast v6, Ljava/lang/Iterable;
00704: new-instance v8, Ljava/util/ArrayList;
00708: move-object/from16 v21, v2
0070c: const/16 v11, 10
00710: invoke-static v6, v11, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
00716: move-result v2
00718: invoke-direct v8, v2, Ljava/util/ArrayList;-><init>(I)V
0071e: invoke-interface v6, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00724: move-result-object v2
00726: invoke-interface v2, Ljava/util/Iterator;->hasNext()Z
0072c: move-result v6
0072e: if-eqz v6, +010h
00732: invoke-interface v2, Ljava/util/Iterator;->next()Ljava/lang/Object;
00738: move-result-object v6
0073a: check-cast v6, Lcom/vk/pushme/logic/Subscription;
0073e: invoke-virtual v6, Lcom/vk/pushme/logic/Subscription;->getAccount()Ljava/lang/String;
00744: move-result-object v6
00746: invoke-interface v8, v6, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
0074c: goto -13h
0074e: invoke-static v8, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;
00754: move-result-object v2
00756: new-instance v6, Ljava/util/LinkedHashSet;
0075a: invoke-direct v6, Ljava/util/LinkedHashSet;-><init>()V
00760: iget-object v8, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->apiCreator Lkotlin/jvm/functions/Function0;
00764: invoke-interface v8, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;
0076a: move-result-object v8
0076c: check-cast v8, Lcom/vk/pushme/network/PushMeApi;
00770: invoke-virtual v3, v7, Lcom/vk/pushme/logic/SubscriptionBatcher;->batch(Ljava/util/Collection;)Ljava/util/Collection;
00776: move-result-object v11
00778: check-cast v11, Ljava/lang/Iterable;
0077c: invoke-interface v11, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00782: move-result-object v22
00784: move-object/from16 v23, v11
00788: move-object v11, v3
0078a: move-object/from16 v3, v17
0078e: move-object/from16 v17, v23
00792: move-object/from16 v23, v7
00796: move-object v7, v6
00798: move-object/from16 v6, v21
0079c: move-object/from16 v21, v23
007a0: move-object/from16 v23, v13
007a4: move-object v13, v2
007a6: move-object/from16 v2, v20
007aa: move-object/from16 v20, v5
007ae: move-object v5, v8
007b0: move v8, v0
007b2: move-object/from16 v0, v35
007b6: move-object/from16 v35, v22
007ba: move-object/from16 v22, v9
007be: move-object/from16 v9, v19
007c2: move-object/from16 v19, v4
007c6: const/4 v4, 0
007c8: invoke-interface/range v35, Ljava/util/Iterator;->hasNext()Z
007ce: move-result v24
007d0: if-eqz v24, +196h
007d4: invoke-interface/range v35, Ljava/util/Iterator;->next()Ljava/lang/Object;
007da: move-result-object v24
007dc: move-object/from16 v25, v14
007e0: move-object/from16 v14, v24
007e4: check-cast v14, Ljava/util/Collection;
007e8: move-object/from16 v26, v10
007ec: move-object v10, v14
007ee: check-cast v10, Ljava/lang/Iterable;
007f2: new-instance v1, Ljava/util/ArrayList;
007f6: move-object/from16 v27, v12
007fa: move-object/from16 v28, v14
007fe: const/16 v12, 10
00802: invoke-static v10, v12, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
00808: move-result v14
0080a: invoke-direct v1, v14, Ljava/util/ArrayList;-><init>(I)V
00810: invoke-interface v10, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00816: move-result-object v10
00818: invoke-interface v10, Ljava/util/Iterator;->hasNext()Z
0081e: move-result v12
00820: if-eqz v12, +01ah
00824: invoke-interface v10, Ljava/util/Iterator;->next()Ljava/lang/Object;
0082a: move-result-object v12
0082c: check-cast v12, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
00830: invoke-virtual v12, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getAccount()Ljava/lang/String;
00836: move-result-object v12
00838: invoke-interface v1, v12, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
0083e: goto -13h
00840: move-exception v0
00842: move-object/from16 v1, v33
00846: goto/16 -35ah
0084a: move-exception v0
0084c: move-object/from16 v1, v33
00850: goto/16 -20dh
00854: invoke-static v1, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;
0085a: move-result-object v1
0085c: invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00862: move-result-object v10
00864: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00868: iput-object v15, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
0086c: invoke-static/range v18, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00872: move-result-object v10
00874: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
00878: invoke-static/range v20, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0087e: move-result-object v10
00880: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00884: invoke-static/range v19, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0088a: move-result-object v10
0088c: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00890: invoke-static/range v30, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00896: move-result-object v10
00898: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
0089c: invoke-static/range v21, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008a2: move-result-object v10
008a4: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
008a8: iput-object v2, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
008ac: iput-object v3, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
008b0: iput-object v6, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
008b4: iput-object v11, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
008b8: iput-object v13, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
008bc: iput-object v7, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
008c0: iput-object v5, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
008c4: invoke-static/range v17, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008ca: move-result-object v10
008cc: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
008d0: move-object/from16 v10, v35
008d4: iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
008d8: invoke-static/range v24, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008de: move-result-object v12
008e0: iput-object v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$16 Ljava/lang/Object;
008e4: invoke-static/range v28, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008ea: move-result-object v12
008ec: iput-object v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
008f0: iput-object v1, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
008f4: iput-boolean v8, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
008f8: iput v4, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
008fc: const/4 v12, 0
008fe: iput v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$1 I
00902: const/4 v12, 3
00904: iput v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00908: move-object/from16 v14, v28
0090c: invoke-interface v5, v14, v9, Lcom/vk/pushme/network/PushMeApi;->setSettingsV1(Ljava/util/Collection; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00912: move-result-object v14
00914: move-object/from16 v12, v27
00918: if-ne v14, v12, +004h
0091c: goto/16 +1b7h
00920: move/from16 v32, v4
00924: move-object v4, v1
00926: move-object v1, v2
00928: move-object v2, v14
0092a: move-object/from16 v14, v21
0092e: move/from16 v21, v8
00932: move-object v8, v6
00934: move/from16 v6, v32
00938: move-object/from16 v32, v7
0093c: move-object v7, v5
0093e: move-object v5, v10
00940: move-object v10, v11
00942: move-object v11, v9
00944: move-object/from16 v9, v32
00948: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult;
0094c: move-object/from16 v35, v1
00950: sget-object v1, Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;->INSTANCE Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;
00954: invoke-static v2, v1, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object; Ljava/lang/Object;)Z
0095a: move-result v1
0095c: if-eqz v1, +025h
00960: move-object/from16 v1, v33
00964: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00968: move-object/from16 v24, v5
0096c: const-string v5, "Api result V1: OK"
00970: move/from16 v27, v6
00974: move-object/from16 v28, v7
00978: const/4 v6, 2
0097a: const/4 v7, 0
0097c: invoke-static v2, v5, v7, v6, v7, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00982: move-object v2, v9
00984: check-cast v2, Ljava/util/Collection;
00988: check-cast v4, Ljava/lang/Iterable;
0098c: invoke-static v2, v4, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00992: move-object/from16 v31, v8
00996: move-object/from16 v7, v23
0099a: move-object/from16 v8, v26
0099e: move-object/from16 v26, v9
009a2: goto/16 +08eh
009a6: move-object/from16 v1, v33
009aa: move-object/from16 v24, v5
009ae: move/from16 v27, v6
009b2: move-object/from16 v28, v7
009b6: instance-of v5, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
009ba: if-eqz v5, +067h
009be: iget-object v5, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
009c2: move-object v6, v2
009c4: check-cast v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
009c8: invoke-virtual v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
009ce: move-result-object v6
009d0: invoke-interface v6, Ljava/util/Set;->size()I
009d6: move-result v6
009d8: new-instance v7, Ljava/lang/StringBuilder;
009dc: invoke-direct v7, Ljava/lang/StringBuilder;-><init>()V
009e2: move-object/from16 v31, v8
009e6: move-object/from16 v8, v26
009ea: invoke-virtual v7, v8, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
009f0: invoke-virtual v7, v6, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
009f6: move-object/from16 v6, v25
009fa: invoke-virtual v7, v6, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00a00: invoke-virtual v7, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00a06: move-result-object v7
00a08: move-object/from16 v25, v6
00a0c: move-object/from16 v26, v9
00a10: const/4 v6, 2
00a12: const/4 v9, 0
00a14: invoke-static v5, v7, v9, v6, v9, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00a1a: iget-object v5, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
00a1e: move-object v6, v2
00a20: check-cast v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00a24: invoke-virtual v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00a2a: move-result-object v6
00a2c: invoke-interface v6, Ljava/util/Set;->size()I
00a32: move-result v6
00a34: new-instance v7, Ljava/lang/StringBuilder;
00a38: invoke-direct v7, Ljava/lang/StringBuilder;-><init>()V
00a3e: invoke-virtual v7, v8, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00a44: invoke-virtual v7, v6, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00a4a: invoke-virtual v7, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00a50: move-result-object v6
00a52: move-object/from16 v7, v23
00a56: invoke-virtual v5, v3, v7, v6, Lcom/vk/pushme/analytcis/AnalyticsHandler;->subscriptionError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
00a5c: move-object/from16 v9, v26
00a60: check-cast v9, Ljava/util/Collection;
00a64: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00a68: invoke-virtual v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00a6e: move-result-object v2
00a70: check-cast v2, Ljava/lang/Iterable;
00a74: invoke-static v4, v2, Lkotlin/collections/SetsKt;->minus(Ljava/util/Set; Ljava/lang/Iterable;)Ljava/util/Set;
00a7a: move-result-object v2
00a7c: check-cast v2, Ljava/lang/Iterable;
00a80: invoke-static v9, v2, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00a86: goto +1ch
00a88: move-object/from16 v31, v8
00a8c: move-object/from16 v7, v23
00a90: move-object/from16 v8, v26
00a94: move-object/from16 v26, v9
00a98: instance-of v4, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00a9c: if-eqz v4, +008h
00aa0: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00aa4: invoke-direct v1, v3, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV1ServerError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;)V
00aaa: goto +ah
00aac: instance-of v4, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00ab0: if-eqz v4, +020h
00ab4: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00ab8: invoke-direct v1, v3, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV1UnknownError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;)V
00abe: move-object/from16 v2, v35
00ac2: move-object/from16 v23, v7
00ac6: move-object v9, v11
00ac8: move-object/from16 v35, v24
00acc: move-object/from16 v7, v26
00ad0: move/from16 v4, v27
00ad4: move-object/from16 v5, v28
00ad8: move-object/from16 v6, v31
00adc: move-object v11, v10
00ade: move-object v10, v8
00ae0: move/from16 v8, v21
00ae4: move-object/from16 v21, v14
00ae8: move-object/from16 v14, v25
00aec: goto/16 -192h
00af0: new-instance v0, Lkotlin/NoWhenBranchMatchedException;
00af4: invoke-direct v0, Lkotlin/NoWhenBranchMatchedException;-><init>()V
00afa: throw v0
00afc: move-object v10, v14
00afe: move-object/from16 v4, v23
00b02: move-object v14, v2
00b04: check-cast v14, Ljava/util/Collection;
00b08: invoke-virtual v11, v14, Lcom/vk/pushme/logic/SubscriptionBatcher;->batch(Ljava/util/Collection;)Ljava/util/Collection;
00b0e: move-result-object v14
00b10: check-cast v14, Ljava/lang/Iterable;
00b14: invoke-interface v14, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00b1a: move-result-object v17
00b1c: move-object/from16 v35, v2
00b20: move-object v2, v15
00b22: move-object/from16 v15, v17
00b26: move-object/from16 v17, v11
00b2a: move-object v11, v7
00b2c: move-object v7, v5
00b2e: move-object v5, v3
00b30: move-object v3, v9
00b32: const/4 v9, 0
00b34: invoke-interface v15, Ljava/util/Iterator;->hasNext()Z
00b3a: move-result v23
00b3c: if-eqz v23, +199h
00b40: invoke-interface v15, Ljava/util/Iterator;->next()Ljava/lang/Object;
00b46: move-result-object v23
00b48: move-object/from16 v24, v5
00b4c: move-object/from16 v5, v23
00b50: check-cast v5, Ljava/util/Collection;
00b54: move-object/from16 v25, v14
00b58: move-object v14, v5
00b5a: check-cast v14, Ljava/lang/Iterable;
00b5e: move-object/from16 v26, v4
00b62: new-instance v4, Ljava/util/ArrayList;
00b66: move-object/from16 v27, v10
00b6a: const/16 v10, 10
00b6e: invoke-static v14, v10, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
00b74: move-result v1
00b76: invoke-direct v4, v1, Ljava/util/ArrayList;-><init>(I)V
00b7c: invoke-interface v14, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00b82: move-result-object v1
00b84: invoke-interface v1, Ljava/util/Iterator;->hasNext()Z
00b8a: move-result v14
00b8c: if-eqz v14, +015h
00b90: invoke-interface v1, Ljava/util/Iterator;->next()Ljava/lang/Object;
00b96: move-result-object v14
00b98: check-cast v14, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
00b9c: invoke-virtual v14, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getAccount()Ljava/lang/String;
00ba2: move-result-object v14
00ba4: invoke-interface v4, v14, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
00baa: goto -13h
00bac: move-exception v0
00bae: move-object/from16 v1, v33
00bb2: goto/16 +1d9h
00bb6: invoke-static v4, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;
00bbc: move-result-object v4
00bbe: invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bc4: move-result-object v1
00bc6: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00bca: iput-object v2, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
00bce: invoke-static/range v18, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bd4: move-result-object v1
00bd6: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
00bda: invoke-static/range v20, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00be0: move-result-object v1
00be2: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00be6: invoke-static/range v19, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bec: move-result-object v1
00bee: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00bf2: invoke-static/range v30, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bf8: move-result-object v1
00bfa: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
00bfe: invoke-static/range v21, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c04: move-result-object v1
00c06: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
00c0a: invoke-static/range v35, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c10: move-result-object v1
00c12: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
00c16: invoke-static/range v24, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c1c: move-result-object v1
00c1e: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
00c22: iput-object v6, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
00c26: invoke-static/range v17, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c2c: move-result-object v1
00c2e: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
00c32: iput-object v13, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
00c36: iput-object v11, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
00c3a: iput-object v7, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
00c3e: invoke-static/range v25, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c44: move-result-object v1
00c46: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
00c4a: iput-object v15, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
00c4e: invoke-static/range v23, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c54: move-result-object v1
00c56: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$16 Ljava/lang/Object;
00c5a: invoke-static v5, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c60: move-result-object v1
00c62: iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
00c66: iput-object v4, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
00c6a: iput-boolean v8, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
00c6e: iput v9, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
00c72: const/4 v1, 0
00c74: iput v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$1 I
00c78: const/4 v14, 4
00c7a: iput v14, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00c7e: invoke-interface v7, v5, v3, Lcom/vk/pushme/network/PushMeApi;->setSettingsV2(Ljava/util/Collection; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00c84: move-result-object v5
00c86: if-ne v5, v12, +003h
00c8a: return-object v12
00c8c: move-object/from16 v16, v15
00c90: move-object v15, v13
00c92: move-object v13, v11
00c94: move-object v11, v3
00c96: move-object v3, v2
00c98: move-object v2, v5
00c9a: move-object/from16 v5, v24
00c9e: move-object/from16 v24, v20
00ca2: move-object/from16 v20, v35
00ca6: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult;
00caa: sget-object v1, Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;->INSTANCE Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;
00cae: invoke-static v2, v1, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object; Ljava/lang/Object;)Z
00cb4: move-result v1
00cb6: if-eqz v1, +02eh
00cba: move-object/from16 v1, v33
00cbe: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00cc2: const-string v10, "Api result V2: OK"
00cc6: move-object/from16 v35, v3
00cca: const/4 v3, 0
00ccc: const/4 v14, 2
00cce: invoke-static v2, v10, v3, v14, v3, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00cd4: move-object v2, v13
00cd6: check-cast v2, Ljava/util/Collection;
00cda: check-cast v4, Ljava/lang/Iterable;
00cde: invoke-static v2, v4, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00ce4: move-object/from16 v28, v5
00ce8: move-object/from16 v5, v22
00cec: move-object/from16 v10, v27
00cf0: move-object/from16 v22, v7
00cf4: move/from16 v27, v8
00cf8: move-object/from16 v8, v26
00cfc: goto/16 +096h
00d00: move-exception v0
00d02: move-object/from16 v2, v35
00d06: goto/16 +12fh
00d0a: move-exception v0
00d0c: move-object/from16 v35, v3
00d10: goto -7h
00d12: move-object/from16 v1, v33
00d16: move-object/from16 v35, v3
00d1a: instance-of v3, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00d1e: if-eqz v3, +066h
00d22: iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00d26: move-object v10, v2
00d28: check-cast v10, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00d2c: invoke-virtual v10, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00d32: move-result-object v10
00d34: invoke-interface v10, Ljava/util/Set;->size()I
00d3a: move-result v10
00d3c: new-instance v14, Ljava/lang/StringBuilder;
00d40: invoke-direct v14, Ljava/lang/StringBuilder;-><init>()V
00d46: move-object/from16 v28, v5
00d4a: move-object/from16 v5, v22
00d4e: invoke-virtual v14, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00d54: invoke-virtual v14, v10, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00d5a: move-object/from16 v10, v27
00d5e: invoke-virtual v14, v10, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00d64: invoke-virtual v14, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00d6a: move-result-object v14
00d6c: move-object/from16 v22, v7
00d70: move/from16 v27, v8
00d74: const/4 v7, 2
00d76: const/4 v8, 0
00d78: invoke-static v3, v14, v8, v7, v8, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00d7e: iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
00d82: move-object v7, v2
00d84: check-cast v7, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00d88: invoke-virtual v7, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00d8e: move-result-object v7
00d90: invoke-interface v7, Ljava/util/Set;->size()I
00d96: move-result v7
00d98: new-instance v8, Ljava/lang/StringBuilder;
00d9c: invoke-direct v8, Ljava/lang/StringBuilder;-><init>()V
00da2: invoke-virtual v8, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00da8: invoke-virtual v8, v7, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00dae: invoke-virtual v8, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00db4: move-result-object v7
00db6: move-object/from16 v8, v26
00dba: invoke-virtual v3, v6, v8, v7, Lcom/vk/pushme/analytcis/AnalyticsHandler;->subscriptionError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
00dc0: move-object v3, v13
00dc2: check-cast v3, Ljava/util/Collection;
00dc6: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00dca: invoke-virtual v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00dd0: move-result-object v2
00dd2: check-cast v2, Ljava/lang/Iterable;
00dd6: invoke-static v4, v2, Lkotlin/collections/SetsKt;->minus(Ljava/util/Set; Ljava/lang/Iterable;)Ljava/util/Set;
00ddc: move-result-object v2
00dde: check-cast v2, Ljava/lang/Iterable;
00de2: invoke-static v3, v2, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00de8: goto +20h
00dea: move-object/from16 v28, v5
00dee: move-object/from16 v5, v22
00df2: move-object/from16 v10, v27
00df6: move-object/from16 v22, v7
00dfa: move/from16 v27, v8
00dfe: move-object/from16 v8, v26
00e02: instance-of v3, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00e06: if-eqz v3, +008h
00e0a: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00e0e: invoke-direct v1, v6, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV2ServerError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;)V
00e14: goto +ah
00e16: instance-of v3, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00e1a: if-eqz v3, +01fh
00e1e: check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00e22: invoke-direct v1, v6, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV2UnknownError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;)V
00e28: move-object/from16 v2, v35
00e2c: move-object v4, v8
00e2e: move-object v3, v11
00e30: move-object v11, v13
00e32: move-object v13, v15
00e34: move-object/from16 v15, v16
00e38: move-object/from16 v35, v20
00e3c: move-object/from16 v7, v22
00e40: move-object/from16 v20, v24
00e44: move-object/from16 v14, v25
00e48: move/from16 v8, v27
00e4c: move-object/from16 v22, v5
00e50: move-object/from16 v5, v28
00e54: goto/16 -190h
00e58: new-instance v0, Lkotlin/NoWhenBranchMatchedException;
00e5c: invoke-direct v0, Lkotlin/NoWhenBranchMatchedException;-><init>()V
00e62: throw v0
00e64: move-exception v0
00e66: move-object/from16 v1, v33
00e6a: goto/16 -0afh
00e6e: invoke-interface v13, Ljava/util/Set;->size()I
00e74: move-result v0
00e76: invoke-interface v11, Ljava/util/Set;->size()I
00e7c: move-result v3
00e7e: if-ne v0, v3, +00eh
00e82: invoke-direct v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleAllAccountsSubscribed(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00e88: move-result-object v0
00e8a: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00e8e: const/4 v4, 1
00e90: const/4 v5, 0
00e92: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00e98: return-object v0
00e9a: invoke-interface v11, Ljava/util/Set;->isEmpty()Z
00ea0: move-result v0
00ea2: if-eqz v0, +00eh
00ea6: invoke-direct v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoAccountsSubscribed(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00eac: move-result-object v0
00eae: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00eb2: const/4 v4, 1
00eb4: const/4 v5, 0
00eb6: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00ebc: return-object v0
00ebe: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00ec2: invoke-interface v11, Ljava/util/Set;->size()I
00ec8: move-result v3
00eca: invoke-interface v13, Ljava/util/Set;->size()I
00ed0: move-result v4
00ed2: new-instance v5, Ljava/lang/StringBuilder;
00ed6: invoke-direct v5, Ljava/lang/StringBuilder;-><init>()V
00edc: invoke-virtual v5, v3, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00ee2: const-string v3, " of "
00ee6: invoke-virtual v5, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00eec: invoke-virtual v5, v4, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00ef2: const-string v3, " accounts are successful"
00ef6: invoke-virtual v5, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00efc: invoke-virtual v5, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00f02: move-result-object v3
00f04: const/4 v5, 0
00f06: const/4 v6, 2
00f08: invoke-static v0, v3, v5, v6, v5, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00f0e: new-instance v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$NoAuthError;
00f12: move-object v3, v11
00f14: check-cast v3, Ljava/lang/Iterable;
00f18: invoke-static v13, v3, Lkotlin/collections/SetsKt;->minus(Ljava/util/Set; Ljava/lang/Iterable;)Ljava/util/Set;
00f1e: move-result-object v3
00f20: invoke-direct v0, v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$NoAuthError;-><init>(Ljava/util/Set; Ljava/util/Set;)V
00f26: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f2a: const/4 v4, 1
00f2c: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f32: return-object v0
00f34: invoke-direct v1, v15, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoAndroidId(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00f3a: move-result-object v0
00f3c: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f40: const/4 v4, 1
00f42: const/4 v5, 0
00f44: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f4a: return-object v0
00f4c: invoke-direct v1, v15, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoDeviceId(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00f52: move-result-object v0
00f54: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f58: const/4 v4, 1
00f5a: const/4 v5, 0
00f5c: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f62: return-object v0
00f64: invoke-direct v1, v2, v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleCouldNotCreateSubscription(Ljava/lang/String; Ljava/lang/Exception;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00f6a: move-result-object v0
00f6c: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f70: const/4 v4, 1
00f72: const/4 v5, 0
00f74: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f7a: return-object v0
00f7c: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f80: invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f86: throw v0
00f88: new-instance v0, Ljava/lang/IllegalArgumentException;
00f8c: const-string v2, "Failed requirement."
00f90: invoke-direct v0, v2, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V
00f96: throw v0
~~~


## Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;::invoke (Ljava/lang/String; Ljava/util/Set; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes19.dex

~~~smali-like
00000: move-object/from16 v1, v19
00004: move-object/from16 v0, v20
00008: move-object/from16 v2, v22
0000c: const-string v3, "Server error occurred, code = "
00010: const-string v4, "toLowerCase(...)"
00014: instance-of v5, v2, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;
00018: if-eqz v5, +011h
0001c: move-object v5, v2
0001e: check-cast v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;
00022: iget v6, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->label I
00026: const/high16 v7, -2147483648
0002a: and-int v8, v6, v7
0002e: if-eqz v8, +006h
00032: sub-int/2addr v6, v7
00034: iput v6, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->label I
00038: goto +6h
0003a: new-instance v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;
0003e: invoke-direct v5, v1, v2, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;-><init>(Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase; Lkotlin/coroutines/Continuation;)V
00044: iget-object v2, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->result Ljava/lang/Object;
00048: invoke-static Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;
0004e: move-result-object v6
00050: iget v7, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->label I
00054: const-string v8, "UNKNOWN_ERROR"
00058: const-string v9, "Failed to unsubscribe"
0005c: const/4 v10, 2
0005e: const/4 v11, 1
00060: const/4 v12, 0
00062: if-eqz v7, +044h
00066: if-eq v7, v11, +036h
0006a: if-ne v7, v10, +02ch
0006e: iget-object v0, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$6 Ljava/lang/Object;
00072: check-cast v0, Ljava/lang/String;
00076: iget-object v7, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$5 Ljava/lang/Object;
0007a: check-cast v7, Ljava/util/Iterator;
0007e: iget-object v13, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$4 Ljava/lang/Object;
00082: check-cast v13, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result;
00086: iget-object v14, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$3 Ljava/lang/Object;
0008a: check-cast v14, Lcom/vk/pushme/network/PushMeApi;
0008e: iget-object v15, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$2 Ljava/lang/Object;
00092: check-cast v15, Ljava/lang/String;
00096: iget-object v11, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$1 Ljava/lang/Object;
0009a: check-cast v11, Ljava/util/Set;
0009e: iget-object v10, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$0 Ljava/lang/Object;
000a2: check-cast v10, Ljava/lang/String;
000a6: invoke-static v2, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000ac: move-object/from16 v16, v8
000b0: goto/16 +0d0h
000b4: move-exception v0
000b6: goto/16 +1c9h
000ba: move-exception v0
000bc: move-object v4, v8
000be: goto/16 +1ach
000c2: new-instance v0, Ljava/lang/IllegalStateException;
000c6: const-string v2, "call to 'resume' before 'invoke' with coroutine"
000ca: invoke-direct v0, v2, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
000d0: throw v0
000d2: iget-object v0, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$1 Ljava/lang/Object;
000d6: check-cast v0, Ljava/util/Set;
000da: iget-object v7, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$0 Ljava/lang/Object;
000de: check-cast v7, Ljava/lang/String;
000e2: invoke-static v2, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000e8: goto +39h
000ea: invoke-static v2, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000f0: invoke-static v0, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
000f6: move-result v2
000f8: if-nez v2, +1b0h
000fc: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logger Lcom/vk/pushme/common/Logger;
00100: new-instance v7, Ljava/lang/StringBuilder;
00104: invoke-direct v7, Ljava/lang/StringBuilder;-><init>()V
0010a: const-string v10, "UseCase started, application: "
0010e: invoke-virtual v7, v10, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00114: invoke-virtual v7, v0, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0011a: invoke-virtual v7, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00120: move-result-object v7
00122: const/4 v10, 2
00124: invoke-static v2, v7, v12, v10, v12, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
0012a: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
0012e: iput-object v0, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$0 Ljava/lang/Object;
00132: move-object/from16 v7, v21
00136: iput-object v7, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$1 Ljava/lang/Object;
0013a: const/4 v10, 1
0013c: iput v10, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->label I
00140: invoke-static v2, v12, v5, v10, v12, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->lock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; Lkotlin/coroutines/Continuation; I Ljava/lang/Object;)Ljava/lang/Object;
00146: move-result-object v2
00148: if-ne v2, v6, +004h
0014c: goto/16 +07fh
00150: move-object/from16 v18, v7
00154: move-object v7, v0
00156: move-object/from16 v0, v18
0015a: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->deviceIdProvider Lcom/vk/pushme/util/provider/DeviceIdProvider;
0015e: invoke-interface v2, Lcom/vk/pushme/util/provider/DeviceIdProvider;->getDeviceId()Ljava/lang/String;
00164: move-result-object v2
00166: if-eqz v2, +008h
0016a: invoke-static v2, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
00170: move-result v10
00172: if-eqz v10, +005h
00176: move-object v4, v8
00178: goto/16 +140h
0017c: iget-object v10, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->apiCreator Lkotlin/jvm/functions/Function0;
00180: invoke-interface v10, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;
00186: move-result-object v10
00188: check-cast v10, Lcom/vk/pushme/network/PushMeApi;
0018c: sget-object v11, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$OK;->INSTANCE Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$OK;
00190: invoke-interface v0, Ljava/util/Set;->iterator()Ljava/util/Iterator;
00196: move-result-object v13
00198: move-object v15, v2
0019a: move-object v14, v10
0019c: move-object v10, v7
0019e: move-object v7, v13
001a0: move-object v13, v11
001a2: move-object v11, v0
001a4: invoke-interface v7, Ljava/util/Iterator;->hasNext()Z
001aa: move-result v0
001ac: if-eqz v0, +102h
001b0: invoke-interface v7, Ljava/util/Iterator;->next()Ljava/lang/Object;
001b6: move-result-object v0
001b8: check-cast v0, Ljava/lang/String;
001bc: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logger Lcom/vk/pushme/common/Logger;
001c0: new-instance v12, Ljava/lang/StringBuilder;
001c4: invoke-direct v12, Ljava/lang/StringBuilder;-><init>()V
001ca: move-object/from16 v20, v11
001ce: const-string v11, "Unsubscribe account: "
001d2: invoke-virtual v12, v11, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
001d8: invoke-virtual v12, v0, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
001de: invoke-virtual v12, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
001e4: move-result-object v11
001e6: move-object/from16 v16, v8
001ea: const/4 v8, 0
001ec: const/4 v12, 2
001ee: invoke-static v2, v11, v8, v12, v8, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
001f4: sget-object v2, Ljava/util/Locale;->ROOT Ljava/util/Locale;
001f8: invoke-virtual v0, v2, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;
001fe: move-result-object v8
00200: invoke-static v8, v4, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object; Ljava/lang/String;)V
00206: invoke-virtual v10, v2, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;
0020c: move-result-object v2
0020e: invoke-static v2, v4, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object; Ljava/lang/String;)V
00214: iput-object v10, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$0 Ljava/lang/Object;
00218: invoke-static/range v20, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0021e: move-result-object v11
00220: iput-object v11, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$1 Ljava/lang/Object;
00224: iput-object v15, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$2 Ljava/lang/Object;
00228: iput-object v14, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$3 Ljava/lang/Object;
0022c: iput-object v13, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$4 Ljava/lang/Object;
00230: iput-object v7, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$5 Ljava/lang/Object;
00234: iput-object v0, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->L$6 Ljava/lang/Object;
00238: const/4 v12, 2
0023a: iput v12, v5, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$invoke$1;->label I
0023e: invoke-interface v14, v8, v15, v2, v5, Lcom/vk/pushme/network/PushMeApi;->unsubscribeByDeviceId(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00244: move-result-object v2
00246: if-ne v2, v6, +003h
0024a: return-object v6
0024c: move-object/from16 v11, v20
00250: check-cast v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult;
00254: sget-object v8, Lcom/vk/pushme/network/model/result/UnsubscribeResult$OK;->INSTANCE Lcom/vk/pushme/network/model/result/UnsubscribeResult$OK;
00258: invoke-static v2, v8, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object; Ljava/lang/Object;)Z
0025e: move-result v8
00260: if-eqz v8, +019h
00264: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logger Lcom/vk/pushme/common/Logger;
00268: const-string v2, "Unsubscribed successful"
0026c: const/4 v8, 0
0026e: const/4 v12, 2
00270: invoke-static v0, v2, v8, v12, v8, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00276: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
0027a: invoke-virtual v0, v10, Lcom/vk/pushme/analytcis/AnalyticsHandler;->successUnsubscribe(Ljava/lang/String;)V
00280: move-object/from16 v8, v16
00284: const/4 v12, 0
00286: goto -71h
00288: move-exception v0
0028a: move-object/from16 v4, v16
0028e: goto/16 +0c4h
00292: instance-of v8, v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult$ServerError;
00296: if-eqz v8, +05dh
0029a: iget-object v8, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logger Lcom/vk/pushme/common/Logger;
0029e: move-object v12, v2
002a0: check-cast v12, Lcom/vk/pushme/network/model/result/UnsubscribeResult$ServerError;
002a4: invoke-virtual v12, Lcom/vk/pushme/network/model/result/UnsubscribeResult$ServerError;->getCode()I
002aa: move-result v12
002ac: new-instance v13, Ljava/lang/StringBuilder;
002b0: invoke-direct v13, Ljava/lang/StringBuilder;-><init>()V
002b6: invoke-virtual v13, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
002bc: invoke-virtual v13, v12, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
002c2: invoke-virtual v13, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
002c8: move-result-object v12
002ca: move-object/from16 v17, v4
002ce: const/4 v4, 0
002d0: const/4 v13, 2
002d2: invoke-static v8, v12, v4, v13, v4, Lcom/vk/pushme/common/Logger;->error$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
002d8: iget-object v4, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
002dc: const-string v8, "SERVER_ERROR"
002e0: check-cast v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult$ServerError;
002e4: invoke-virtual v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult$ServerError;->getCode()I
002ea: move-result v2
002ec: new-instance v12, Ljava/lang/StringBuilder;
002f0: invoke-direct v12, Ljava/lang/StringBuilder;-><init>()V
002f6: invoke-virtual v12, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
002fc: invoke-virtual v12, v2, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00302: invoke-virtual v12, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00308: move-result-object v2
0030a: invoke-virtual v4, v10, v8, v2, Lcom/vk/pushme/analytcis/AnalyticsHandler;->unsubscribeError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
00310: new-instance v2, Ljava/lang/RuntimeException;
00314: new-instance v4, Ljava/lang/StringBuilder;
00318: invoke-direct v4, Ljava/lang/StringBuilder;-><init>()V
0031e: const-string v8, "Unable to unsubscribe "
00322: invoke-virtual v4, v8, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00328: invoke-virtual v4, v0, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0032e: invoke-virtual v4, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00334: move-result-object v0
00336: invoke-direct v2, v0, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V
0033c: new-instance v13, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;
00340: invoke-direct v13, v2, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;-><init>(Ljava/lang/Throwable;)V
00346: move-object/from16 v8, v16
0034a: move-object/from16 v4, v17
0034e: goto -65h
00350: move-object/from16 v17, v4
00354: instance-of v0, v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;
00358: if-eqz v0, +024h
0035c: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logger Lcom/vk/pushme/common/Logger;
00360: move-object v4, v2
00362: check-cast v4, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;
00366: invoke-virtual v4, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;->getT()Ljava/lang/Throwable;
0036c: move-result-object v4
0036e: invoke-interface v0, v9, v4, Lcom/vk/pushme/common/Logger;->error(Ljava/lang/String; Ljava/lang/Throwable;)V
00374: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
00378: move-object/from16 v4, v16
0037c: invoke-virtual v0, v10, v4, v9, Lcom/vk/pushme/analytcis/AnalyticsHandler;->unsubscribeError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
00382: new-instance v13, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;
00386: check-cast v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;
0038a: invoke-virtual v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;->getT()Ljava/lang/Throwable;
00390: move-result-object v0
00392: invoke-direct v13, v0, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;-><init>(Ljava/lang/Throwable;)V
00398: move-object v8, v4
0039a: goto -28h
0039c: move-exception v0
0039e: goto +3ch
003a0: move-object/from16 v4, v16
003a4: new-instance v0, Lkotlin/NoWhenBranchMatchedException;
003a8: invoke-direct v0, Lkotlin/NoWhenBranchMatchedException;-><init>()V
003ae: throw v0
003b0: move-object v4, v8
003b2: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logger Lcom/vk/pushme/common/Logger;
003b6: new-instance v2, Ljava/lang/StringBuilder;
003ba: invoke-direct v2, Ljava/lang/StringBuilder;-><init>()V
003c0: const-string v3, "Final result is "
003c4: invoke-virtual v2, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
003ca: invoke-virtual v2, v13, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;
003d0: invoke-virtual v2, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
003d6: move-result-object v2
003d8: const/4 v8, 0
003da: const/4 v12, 2
003dc: invoke-static v0, v2, v8, v12, v8, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
003e2: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
003e6: const/4 v10, 1
003e8: invoke-static v0, v8, v10, v8, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
003ee: return-object v13
003f0: move-exception v0
003f2: move-object v4, v8
003f4: move-object v10, v7
003f6: goto +10h
003f8: invoke-direct v1, v7, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logEmptyDeviceId(Ljava/lang/String;)V
003fe: sget-object v0, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$MissingDeviceIdError;->INSTANCE Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$MissingDeviceIdError;
00402: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00406: const/4 v8, 0
00408: const/4 v10, 1
0040a: invoke-static v2, v8, v10, v8, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00410: return-object v0
00412: move-exception v0
00414: goto -10h
00416: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->logger Lcom/vk/pushme/common/Logger;
0041a: const-string v3, "Failed to unsubscribe due to exception"
0041e: invoke-interface v2, v3, v0, Lcom/vk/pushme/common/Logger;->error(Ljava/lang/String; Ljava/lang/Throwable;)V
00424: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
00428: invoke-virtual v2, v10, v4, v9, Lcom/vk/pushme/analytcis/AnalyticsHandler;->unsubscribeError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
0042e: new-instance v2, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;
00432: invoke-direct v2, v0, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;-><init>(Ljava/lang/Throwable;)V
00438: iget-object v0, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
0043c: const/4 v8, 0
0043e: const/4 v10, 1
00440: invoke-static v0, v8, v10, v8, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00446: return-object v2
00448: iget-object v2, v1, Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
0044c: const/4 v8, 0
0044e: const/4 v10, 1
00450: invoke-static v2, v8, v10, v8, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00456: throw v0
00458: new-instance v0, Ljava/lang/IllegalArgumentException;
0045c: const-string v2, "Failed requirement."
00460: invoke-direct v0, v2, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V
00466: throw v0
~~~


## Lcom/vk/pushme/network/PushMeApiImpl;::setSettingsInternal (Ljava/util/Collection; Lcom/vk/pushme/network/ApiVersion; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes12.dex

~~~smali-like
00000: move-object/from16 v1, v17
00004: move-object/from16 v0, v20
00008: instance-of v2, v0, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;
0000c: if-eqz v2, +011h
00010: move-object v2, v0
00012: check-cast v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;
00016: iget v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
0001a: const/high16 v4, -2147483648
0001e: and-int v5, v3, v4
00022: if-eqz v5, +006h
00026: sub-int/2addr v3, v4
00028: iput v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
0002c: goto +6h
0002e: new-instance v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;
00032: invoke-direct v2, v1, v0, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;-><init>(Lcom/vk/pushme/network/PushMeApiImpl; Lkotlin/coroutines/Continuation;)V
00038: iget-object v0, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->result Ljava/lang/Object;
0003c: invoke-static Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;
00042: move-result-object v3
00044: iget v4, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
00048: const/4 v5, 2
0004a: const/4 v6, 1
0004c: if-eqz v4, +061h
00050: if-eq v4, v6, +034h
00054: if-ne v4, v5, +02ah
00058: iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$7 Ljava/lang/Object;
0005c: check-cast v3, Lokhttp3/Response;
00060: iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
00064: check-cast v3, Lokhttp3/Call;
00068: iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
0006c: check-cast v3, Lokhttp3/Request;
00070: iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
00074: check-cast v3, Ljava/lang/String;
00078: iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
0007c: check-cast v3, Ljava/util/Collection;
00080: iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
00084: check-cast v3, Lokhttp3/HttpUrl;
00088: iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
0008c: check-cast v3, Lcom/vk/pushme/network/ApiVersion;
00090: iget-object v2, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
00094: check-cast v2, Ljava/util/Collection;
00098: invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
0009e: goto/16 +11eh
000a2: move-exception v0
000a4: goto/16 +14dh
000a8: new-instance v0, Ljava/lang/IllegalStateException;
000ac: const-string v2, "call to 'resume' before 'invoke' with coroutine"
000b0: invoke-direct v0, v2, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
000b6: throw v0
000b8: iget v4, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->I$0 I
000bc: iget-object v6, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
000c0: check-cast v6, Lokhttp3/Call;
000c4: iget-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
000c8: check-cast v7, Lokhttp3/Request;
000cc: iget-object v8, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
000d0: check-cast v8, Ljava/lang/String;
000d4: iget-object v9, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
000d8: check-cast v9, Ljava/util/Collection;
000dc: iget-object v10, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
000e0: check-cast v10, Lokhttp3/HttpUrl;
000e4: iget-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
000e8: check-cast v11, Lcom/vk/pushme/network/ApiVersion;
000ec: iget-object v12, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
000f0: check-cast v12, Ljava/util/Collection;
000f4: invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000fa: move-object/from16 v16, v8
000fe: move-object v8, v7
00100: move-object v7, v11
00102: move-object v11, v10
00104: move-object v10, v9
00106: move-object/from16 v9, v16
0010a: goto/16 +09bh
0010e: invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00114: invoke-direct v1, Lcom/vk/pushme/network/PushMeApiImpl;->urlBuilder()Lokhttp3/HttpUrl$Builder;
0011a: move-result-object v0
0011c: iget-object v4, v1, Lcom/vk/pushme/network/PushMeApiImpl;->hostInfoProvider Lcom/vk/pushme/network/HostInfoProvider;
00120: move-object/from16 v7, v19
00124: invoke-interface v4, v7, Lcom/vk/pushme/network/HostInfoProvider;->getApiPathByVersion(Lcom/vk/pushme/network/ApiVersion;)Ljava/lang/String;
0012a: move-result-object v4
0012c: invoke-virtual v0, v4, Lokhttp3/HttpUrl$Builder;->addPathSegment(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
00132: move-result-object v0
00134: invoke-virtual v7, Lcom/vk/pushme/network/ApiVersion;->getValue()Ljava/lang/String;
0013a: move-result-object v4
0013c: invoke-virtual v0, v4, Lokhttp3/HttpUrl$Builder;->addPathSegment(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
00142: move-result-object v0
00144: const-string/jumbo v4, set_settings
0014a: invoke-virtual v0, v4, Lokhttp3/HttpUrl$Builder;->addPathSegment(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
00150: move-result-object v0
00152: invoke-virtual v0, Lokhttp3/HttpUrl$Builder;->build()Lokhttp3/HttpUrl;
00158: move-result-object v10
0015a: invoke-direct/range v17 ... v18, Lcom/vk/pushme/network/PushMeApiImpl;->mapSubscriptions(Ljava/util/Collection;)Ljava/util/Collection;
00160: move-result-object v9
00162: sget-object v0, Lkotlinx/serialization/json/Json;->Default Lkotlinx/serialization/json/Json$Default;
00166: invoke-virtual v0, Lkotlinx/serialization/json/Json;->getSerializersModule()Lkotlinx/serialization/modules/SerializersModule;
0016c: new-instance v4, Lkotlinx/serialization/internal/ArrayListSerializer;
00170: sget-object v8, Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest;->Companion Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest$Companion;
00174: invoke-virtual v8, Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest$Companion;->serializer()Lkotlinx/serialization/KSerializer;
0017a: move-result-object v8
0017c: invoke-direct v4, v8, Lkotlinx/serialization/internal/ArrayListSerializer;-><init>(Lkotlinx/serialization/KSerializer;)V
00182: invoke-virtual v0, v4, v9, Lkotlinx/serialization/json/Json;->encodeToString(Lkotlinx/serialization/SerializationStrategy; Ljava/lang/Object;)Ljava/lang/String;
00188: move-result-object v8
0018a: new-instance v0, Lokhttp3/Request$Builder;
0018e: invoke-direct v0, Lokhttp3/Request$Builder;-><init>()V
00194: invoke-virtual v0, v10, Lokhttp3/Request$Builder;->url(Lokhttp3/HttpUrl;)Lokhttp3/Request$Builder;
0019a: move-result-object v0
0019c: invoke-static v8, Lcom/vk/pushme/network/util/ExtensionsKt;->toJsonRequestBody(Ljava/lang/String;)Lokhttp3/RequestBody;
001a2: move-result-object v4
001a4: invoke-virtual v0, v4, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;
001aa: move-result-object v0
001ac: invoke-virtual v0, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;
001b2: move-result-object v0
001b4: iget-object v4, v1, Lcom/vk/pushme/network/PushMeApiImpl;->okHttpClient Lokhttp3/OkHttpClient;
001b8: invoke-virtual v4, v0, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;
001be: move-result-object v4
001c0: invoke-static/range v18, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001c6: move-result-object v11
001c8: iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
001cc: invoke-static v7, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001d2: move-result-object v11
001d4: iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
001d8: invoke-static v10, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001de: move-result-object v11
001e0: iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
001e4: invoke-static v9, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001ea: move-result-object v11
001ec: iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
001f0: invoke-static v8, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001f6: move-result-object v11
001f8: iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
001fc: invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00202: move-result-object v11
00204: iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
00208: invoke-static v4, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0020e: move-result-object v11
00210: iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
00214: const/4 v11, 0
00216: iput v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->I$0 I
0021a: iput v6, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
0021e: invoke-static v4, v2, Lcom/vk/pushme/network/util/CallHandlerKt;->await(Lokhttp3/Call; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00224: move-result-object v6
00226: if-ne v6, v3, +003h
0022a: goto +57h
0022c: move-object v12, v8
0022e: move-object v8, v0
00230: move-object v0, v6
00232: move-object v6, v4
00234: move v4, v11
00236: move-object v11, v10
00238: move-object v10, v9
0023a: move-object v9, v12
0023c: move-object/from16 v12, v18
00240: check-cast v0, Lokhttp3/Response;
00244: invoke-virtual v0, Lokhttp3/Response;->isSuccessful()Z
0024a: move-result v13
0024c: if-eqz v13, +061h
00250: invoke-static Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;
00256: move-result-object v13
00258: new-instance v14, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;
0025c: const/4 v15, 0
0025e: invoke-direct v14, v0, v15, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;-><init>(Lokhttp3/Response; Lkotlin/coroutines/Continuation;)V
00264: invoke-static v12, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0026a: move-result-object v12
0026c: iput-object v12, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
00270: invoke-static v7, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00276: move-result-object v7
00278: iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
0027c: invoke-static v11, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00282: move-result-object v7
00284: iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
00288: invoke-static v10, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0028e: move-result-object v7
00290: iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
00294: invoke-static v9, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0029a: move-result-object v7
0029c: iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
002a0: invoke-static v8, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002a6: move-result-object v7
002a8: iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
002ac: invoke-static v6, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002b2: move-result-object v6
002b4: iput-object v6, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
002b8: invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002be: move-result-object v0
002c0: iput-object v0, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$7 Ljava/lang/Object;
002c4: iput v4, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->I$0 I
002c8: iput v5, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
002cc: invoke-static v13, v14, v2, Lkotlinx/coroutines/BuildersKt;->withContext(Lkotlin/coroutines/CoroutineContext; Lkotlin/jvm/functions/Function2; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
002d2: move-result-object v0
002d4: if-ne v0, v3, +003h
002d8: return-object v3
002da: check-cast v0, Ljava/lang/String;
002de: sget-object v2, Lkotlinx/serialization/json/Json;->Default Lkotlinx/serialization/json/Json$Default;
002e2: invoke-virtual v2, Lkotlinx/serialization/json/Json;->getSerializersModule()Lkotlinx/serialization/modules/SerializersModule;
002e8: sget-object v3, Lcom/vk/pushme/network/model/response/SubscriptionResponse;->Companion Lcom/vk/pushme/network/model/response/SubscriptionResponse$Companion;
002ec: invoke-virtual v3, Lcom/vk/pushme/network/model/response/SubscriptionResponse$Companion;->serializer()Lkotlinx/serialization/KSerializer;
002f2: move-result-object v3
002f4: check-cast v3, Lkotlinx/serialization/DeserializationStrategy;
002f8: invoke-virtual v2, v3, v0, Lkotlinx/serialization/json/Json;->decodeFromString(Lkotlinx/serialization/DeserializationStrategy; Ljava/lang/String;)Ljava/lang/Object;
002fe: move-result-object v0
00300: check-cast v0, Lcom/vk/pushme/network/model/response/SubscriptionResponse;
00304: invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
0030a: move-result-object v0
0030c: goto +23h
0030e: new-instance v2, Lcom/vk/pushme/network/PushMeRequestException;
00312: invoke-virtual v0, Lokhttp3/Response;->message()Ljava/lang/String;
00318: move-result-object v3
0031a: invoke-virtual v0, Lokhttp3/Response;->code()I
00320: move-result v0
00322: invoke-direct v2, v3, v0, Lcom/vk/pushme/network/PushMeRequestException;-><init>(Ljava/lang/String; I)V
00328: sget-object v0, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
0032c: invoke-static v2, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
00332: move-result-object v0
00334: invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
0033a: move-result-object v0
0033c: goto +bh
0033e: sget-object v2, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
00342: invoke-static v0, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
00348: move-result-object v0
0034a: invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
00350: move-result-object v0
00352: invoke-static v0, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z
00358: move-result v2
0035a: if-eqz v2, +00ch
0035e: invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00364: check-cast v0, Lcom/vk/pushme/network/model/response/SubscriptionResponse;
00368: invoke-direct v1, v0, Lcom/vk/pushme/network/PushMeApiImpl;->parseSubscriptionResponseToResult(Lcom/vk/pushme/network/model/response/SubscriptionResponse;)Lcom/vk/pushme/network/model/result/SubscriptionResult;
0036e: move-result-object v0
00370: goto +eh
00372: new-instance v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00376: invoke-static v0, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;
0037c: move-result-object v0
0037e: invoke-static v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V
00384: invoke-direct v2, v0, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;-><init>(Ljava/lang/Throwable;)V
0038a: move-object v0, v2
0038c: return-object v0
~~~


## Lcom/vk/pushme/network/PushMeApiImpl;::unsubscribeByDeviceId (Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes12.dex

~~~smali-like
00000: move-object/from16 v1, v17
00004: move-object/from16 v0, v20
00008: move-object/from16 v2, v21
0000c: instance-of v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;
00010: if-eqz v3, +011h
00014: move-object v3, v2
00016: check-cast v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;
0001a: iget v4, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->label I
0001e: const/high16 v5, -2147483648
00022: and-int v6, v4, v5
00026: if-eqz v6, +006h
0002a: sub-int/2addr v4, v5
0002c: iput v4, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->label I
00030: goto +6h
00032: new-instance v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;
00036: invoke-direct v3, v1, v2, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;-><init>(Lcom/vk/pushme/network/PushMeApiImpl; Lkotlin/coroutines/Continuation;)V
0003c: iget-object v2, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->result Ljava/lang/Object;
00040: invoke-static Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;
00046: move-result-object v4
00048: iget v5, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->label I
0004c: const/4 v6, 2
0004e: const/4 v7, 0
00050: const/4 v8, 1
00052: if-eqz v5, +063h
00056: if-eq v5, v8, +034h
0005a: if-ne v5, v6, +02ah
0005e: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$7 Ljava/lang/Object;
00062: check-cast v0, Lokhttp3/Response;
00066: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$6 Ljava/lang/Object;
0006a: check-cast v0, Lokhttp3/Call;
0006e: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$5 Ljava/lang/Object;
00072: check-cast v0, Lokhttp3/Request;
00076: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$4 Ljava/lang/Object;
0007a: check-cast v0, Lokhttp3/FormBody;
0007e: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$3 Ljava/lang/Object;
00082: check-cast v0, Lokhttp3/HttpUrl;
00086: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$2 Ljava/lang/Object;
0008a: check-cast v0, Ljava/lang/String;
0008e: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$1 Ljava/lang/Object;
00092: check-cast v0, Ljava/lang/String;
00096: iget-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$0 Ljava/lang/Object;
0009a: check-cast v0, Ljava/lang/String;
0009e: invoke-static v2, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000a4: goto/16 +114h
000a8: move-exception v0
000aa: goto/16 +143h
000ae: new-instance v0, Ljava/lang/IllegalStateException;
000b2: const-string v2, "call to 'resume' before 'invoke' with coroutine"
000b6: invoke-direct v0, v2, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
000bc: throw v0
000be: iget v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->I$0 I
000c2: iget-object v5, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$6 Ljava/lang/Object;
000c6: check-cast v5, Lokhttp3/Call;
000ca: iget-object v8, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$5 Ljava/lang/Object;
000ce: check-cast v8, Lokhttp3/Request;
000d2: iget-object v9, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$4 Ljava/lang/Object;
000d6: check-cast v9, Lokhttp3/FormBody;
000da: iget-object v10, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$3 Ljava/lang/Object;
000de: check-cast v10, Lokhttp3/HttpUrl;
000e2: iget-object v11, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$2 Ljava/lang/Object;
000e6: check-cast v11, Ljava/lang/String;
000ea: iget-object v12, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$1 Ljava/lang/Object;
000ee: check-cast v12, Ljava/lang/String;
000f2: iget-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$0 Ljava/lang/Object;
000f6: check-cast v13, Ljava/lang/String;
000fa: invoke-static v2, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00100: move-object/from16 v16, v2
00104: move v2, v0
00106: move-object v0, v11
00108: move-object v11, v12
0010a: move-object v12, v10
0010c: move-object v10, v9
0010e: move-object v9, v8
00110: move-object/from16 v8, v16
00114: goto/16 +090h
00118: invoke-static v2, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
0011e: invoke-direct v1, Lcom/vk/pushme/network/PushMeApiImpl;->urlBuilder()Lokhttp3/HttpUrl$Builder;
00124: move-result-object v2
00126: const-string v5, "api/v1/unsubscribe_by_device_id"
0012a: invoke-virtual v2, v5, Lokhttp3/HttpUrl$Builder;->addPathSegments(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
00130: move-result-object v2
00132: invoke-virtual v2, Lokhttp3/HttpUrl$Builder;->build()Lokhttp3/HttpUrl;
00138: move-result-object v10
0013a: new-instance v2, Lokhttp3/FormBody$Builder;
0013e: invoke-direct v2, v7, v8, v7, Lokhttp3/FormBody$Builder;-><init>(Ljava/nio/charset/Charset; I Lkotlin/jvm/internal/DefaultConstructorMarker;)V
00144: const-string v5, "account"
00148: move-object/from16 v9, v18
0014c: invoke-virtual v2, v5, v9, Lokhttp3/FormBody$Builder;->add(Ljava/lang/String; Ljava/lang/String;)Lokhttp3/FormBody$Builder;
00152: move-result-object v2
00154: const-string v5, "device_id"
00158: move-object/from16 v11, v19
0015c: invoke-virtual v2, v5, v11, Lokhttp3/FormBody$Builder;->add(Ljava/lang/String; Ljava/lang/String;)Lokhttp3/FormBody$Builder;
00162: move-result-object v2
00164: if-eqz v0, +00eh
00168: invoke-static v0, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
0016e: move-result v5
00170: if-eqz v5, +003h
00174: goto +6h
00176: const-string v5, "application"
0017a: invoke-virtual v2, v5, v0, Lokhttp3/FormBody$Builder;->add(Ljava/lang/String; Ljava/lang/String;)Lokhttp3/FormBody$Builder;
00180: invoke-virtual v2, Lokhttp3/FormBody$Builder;->build()Lokhttp3/FormBody;
00186: move-result-object v2
00188: new-instance v5, Lokhttp3/Request$Builder;
0018c: invoke-direct v5, Lokhttp3/Request$Builder;-><init>()V
00192: invoke-virtual v5, v10, Lokhttp3/Request$Builder;->url(Lokhttp3/HttpUrl;)Lokhttp3/Request$Builder;
00198: move-result-object v5
0019a: invoke-virtual v5, v2, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;
001a0: move-result-object v5
001a2: invoke-virtual v5, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;
001a8: move-result-object v5
001aa: iget-object v12, v1, Lcom/vk/pushme/network/PushMeApiImpl;->okHttpClient Lokhttp3/OkHttpClient;
001ae: invoke-virtual v12, v5, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;
001b4: move-result-object v12
001b6: invoke-static v9, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001bc: move-result-object v13
001be: iput-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$0 Ljava/lang/Object;
001c2: invoke-static v11, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001c8: move-result-object v13
001ca: iput-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$1 Ljava/lang/Object;
001ce: invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001d4: move-result-object v13
001d6: iput-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$2 Ljava/lang/Object;
001da: invoke-static v10, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001e0: move-result-object v13
001e2: iput-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$3 Ljava/lang/Object;
001e6: invoke-static v2, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001ec: move-result-object v13
001ee: iput-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$4 Ljava/lang/Object;
001f2: invoke-static v5, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001f8: move-result-object v13
001fa: iput-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$5 Ljava/lang/Object;
001fe: invoke-static v12, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00204: move-result-object v13
00206: iput-object v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$6 Ljava/lang/Object;
0020a: const/4 v13, 0
0020c: iput v13, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->I$0 I
00210: iput v8, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->label I
00214: invoke-static v12, v3, Lcom/vk/pushme/network/util/CallHandlerKt;->await(Lokhttp3/Call; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
0021a: move-result-object v8
0021c: if-ne v8, v4, +003h
00220: goto +55h
00222: move-object/from16 v16, v10
00226: move-object v10, v2
00228: move v2, v13
0022a: move-object v13, v9
0022c: move-object v9, v5
0022e: move-object v5, v12
00230: move-object/from16 v12, v16
00234: check-cast v8, Lokhttp3/Response;
00238: invoke-virtual v8, Lokhttp3/Response;->isSuccessful()Z
0023e: move-result v14
00240: if-eqz v14, +060h
00244: invoke-static Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;
0024a: move-result-object v14
0024c: new-instance v15, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;
00250: invoke-direct v15, v8, v7, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;-><init>(Lokhttp3/Response; Lkotlin/coroutines/Continuation;)V
00256: invoke-static v13, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0025c: move-result-object v7
0025e: iput-object v7, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$0 Ljava/lang/Object;
00262: invoke-static v11, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00268: move-result-object v7
0026a: iput-object v7, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$1 Ljava/lang/Object;
0026e: invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00274: move-result-object v0
00276: iput-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$2 Ljava/lang/Object;
0027a: invoke-static v12, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00280: move-result-object v0
00282: iput-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$3 Ljava/lang/Object;
00286: invoke-static v10, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0028c: move-result-object v0
0028e: iput-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$4 Ljava/lang/Object;
00292: invoke-static v9, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00298: move-result-object v0
0029a: iput-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$5 Ljava/lang/Object;
0029e: invoke-static v5, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002a4: move-result-object v0
002a6: iput-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$6 Ljava/lang/Object;
002aa: invoke-static v8, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002b0: move-result-object v0
002b2: iput-object v0, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->L$7 Ljava/lang/Object;
002b6: iput v2, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->I$0 I
002ba: iput v6, v3, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByDeviceId$1;->label I
002be: invoke-static v14, v15, v3, Lkotlinx/coroutines/BuildersKt;->withContext(Lkotlin/coroutines/CoroutineContext; Lkotlin/jvm/functions/Function2; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
002c4: move-result-object v2
002c6: if-ne v2, v4, +003h
002ca: return-object v4
002cc: check-cast v2, Ljava/lang/String;
002d0: sget-object v0, Lkotlinx/serialization/json/Json;->Default Lkotlinx/serialization/json/Json$Default;
002d4: invoke-virtual v0, Lkotlinx/serialization/json/Json;->getSerializersModule()Lkotlinx/serialization/modules/SerializersModule;
002da: sget-object v3, Lcom/vk/pushme/network/model/response/UnsubscribeResponse;->Companion Lcom/vk/pushme/network/model/response/UnsubscribeResponse$Companion;
002de: invoke-virtual v3, Lcom/vk/pushme/network/model/response/UnsubscribeResponse$Companion;->serializer()Lkotlinx/serialization/KSerializer;
002e4: move-result-object v3
002e6: check-cast v3, Lkotlinx/serialization/DeserializationStrategy;
002ea: invoke-virtual v0, v3, v2, Lkotlinx/serialization/json/Json;->decodeFromString(Lkotlinx/serialization/DeserializationStrategy; Ljava/lang/String;)Ljava/lang/Object;
002f0: move-result-object v0
002f2: check-cast v0, Lcom/vk/pushme/network/model/response/UnsubscribeResponse;
002f6: invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
002fc: move-result-object v0
002fe: goto +23h
00300: new-instance v0, Lcom/vk/pushme/network/PushMeRequestException;
00304: invoke-virtual v8, Lokhttp3/Response;->message()Ljava/lang/String;
0030a: move-result-object v2
0030c: invoke-virtual v8, Lokhttp3/Response;->code()I
00312: move-result v3
00314: invoke-direct v0, v2, v3, Lcom/vk/pushme/network/PushMeRequestException;-><init>(Ljava/lang/String; I)V
0031a: sget-object v2, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
0031e: invoke-static v0, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
00324: move-result-object v0
00326: invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
0032c: move-result-object v0
0032e: goto +bh
00330: sget-object v2, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
00334: invoke-static v0, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
0033a: move-result-object v0
0033c: invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
00342: move-result-object v0
00344: invoke-static v0, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z
0034a: move-result v2
0034c: if-eqz v2, +00ch
00350: invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00356: check-cast v0, Lcom/vk/pushme/network/model/response/UnsubscribeResponse;
0035a: invoke-direct v1, v0, Lcom/vk/pushme/network/PushMeApiImpl;->parseUnsubscribeResponseToResult(Lcom/vk/pushme/network/model/response/UnsubscribeResponse;)Lcom/vk/pushme/network/model/result/UnsubscribeResult;
00360: move-result-object v0
00362: goto +eh
00364: new-instance v2, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;
00368: invoke-static v0, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;
0036e: move-result-object v0
00370: invoke-static v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V
00376: invoke-direct v2, v0, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;-><init>(Ljava/lang/Throwable;)V
0037c: move-object v0, v2
0037e: return-object v0
~~~


## Lcom/vk/pushme/network/PushMeApiImpl;::unsubscribeByToken (Ljava/lang/String; Ljava/lang/String; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
Source APK member: Mail-15.107.0.148045.apk; DEX: classes12.dex

~~~smali-like
00000: instance-of v0, v15, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;
00004: if-eqz v0, +011h
00008: move-object v0, v15
0000a: check-cast v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;
0000e: iget v1, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->label I
00012: const/high16 v2, -2147483648
00016: and-int v3, v1, v2
0001a: if-eqz v3, +006h
0001e: sub-int/2addr v1, v2
00020: iput v1, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->label I
00024: goto +6h
00026: new-instance v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;
0002a: invoke-direct v0, v12, v15, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;-><init>(Lcom/vk/pushme/network/PushMeApiImpl; Lkotlin/coroutines/Continuation;)V
00030: iget-object v15, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->result Ljava/lang/Object;
00034: invoke-static Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;
0003a: move-result-object v1
0003c: iget v2, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->label I
00040: const/4 v3, 2
00042: const/4 v4, 0
00044: const/4 v5, 1
00046: if-eqz v2, +056h
0004a: if-eq v2, v5, +030h
0004e: if-ne v2, v3, +026h
00052: iget-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$6 Ljava/lang/Object;
00056: check-cast v13, Lokhttp3/Response;
0005a: iget-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$5 Ljava/lang/Object;
0005e: check-cast v13, Lokhttp3/Call;
00062: iget-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$4 Ljava/lang/Object;
00066: check-cast v13, Lokhttp3/Request;
0006a: iget-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$3 Ljava/lang/Object;
0006e: check-cast v13, Lokhttp3/FormBody;
00072: iget-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$2 Ljava/lang/Object;
00076: check-cast v13, Lokhttp3/HttpUrl;
0007a: iget-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$1 Ljava/lang/Object;
0007e: check-cast v13, Ljava/lang/String;
00082: iget-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$0 Ljava/lang/Object;
00086: check-cast v13, Ljava/lang/String;
0008a: invoke-static v15, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00090: goto/16 +0e8h
00094: move-exception v13
00096: goto/16 +117h
0009a: new-instance v13, Ljava/lang/IllegalStateException;
0009e: const-string v14, "call to 'resume' before 'invoke' with coroutine"
000a2: invoke-direct v13, v14, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
000a8: throw v13
000aa: iget v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->I$0 I
000ae: iget-object v14, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$5 Ljava/lang/Object;
000b2: check-cast v14, Lokhttp3/Call;
000b6: iget-object v2, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$4 Ljava/lang/Object;
000ba: check-cast v2, Lokhttp3/Request;
000be: iget-object v5, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$3 Ljava/lang/Object;
000c2: check-cast v5, Lokhttp3/FormBody;
000c6: iget-object v6, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$2 Ljava/lang/Object;
000ca: check-cast v6, Lokhttp3/HttpUrl;
000ce: iget-object v7, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$1 Ljava/lang/Object;
000d2: check-cast v7, Ljava/lang/String;
000d6: iget-object v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$0 Ljava/lang/Object;
000da: check-cast v8, Ljava/lang/String;
000de: invoke-static v15, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000e4: move-object v11, v8
000e6: move v8, v13
000e8: move-object v13, v11
000ea: move-object v11, v7
000ec: move-object v7, v14
000ee: move-object v14, v11
000f0: goto +72h
000f2: invoke-static v15, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000f8: invoke-direct v12, Lcom/vk/pushme/network/PushMeApiImpl;->urlBuilder()Lokhttp3/HttpUrl$Builder;
000fe: move-result-object v15
00100: const-string v2, "api/v2/unsubscribe_by_token"
00104: invoke-virtual v15, v2, Lokhttp3/HttpUrl$Builder;->addPathSegments(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
0010a: move-result-object v15
0010c: invoke-virtual v15, Lokhttp3/HttpUrl$Builder;->build()Lokhttp3/HttpUrl;
00112: move-result-object v6
00114: new-instance v15, Lokhttp3/FormBody$Builder;
00118: invoke-direct v15, v4, v5, v4, Lokhttp3/FormBody$Builder;-><init>(Ljava/nio/charset/Charset; I Lkotlin/jvm/internal/DefaultConstructorMarker;)V
0011e: const-string/jumbo v2, token
00124: invoke-virtual v15, v2, v13, Lokhttp3/FormBody$Builder;->add(Ljava/lang/String; Ljava/lang/String;)Lokhttp3/FormBody$Builder;
0012a: move-result-object v15
0012c: const-string v2, "application"
00130: invoke-virtual v15, v2, v14, Lokhttp3/FormBody$Builder;->add(Ljava/lang/String; Ljava/lang/String;)Lokhttp3/FormBody$Builder;
00136: move-result-object v15
00138: invoke-virtual v15, Lokhttp3/FormBody$Builder;->build()Lokhttp3/FormBody;
0013e: move-result-object v15
00140: new-instance v2, Lokhttp3/Request$Builder;
00144: invoke-direct v2, Lokhttp3/Request$Builder;-><init>()V
0014a: invoke-virtual v2, v6, Lokhttp3/Request$Builder;->url(Lokhttp3/HttpUrl;)Lokhttp3/Request$Builder;
00150: move-result-object v2
00152: invoke-virtual v2, v15, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;
00158: move-result-object v2
0015a: invoke-virtual v2, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;
00160: move-result-object v2
00162: iget-object v7, v12, Lcom/vk/pushme/network/PushMeApiImpl;->okHttpClient Lokhttp3/OkHttpClient;
00166: invoke-virtual v7, v2, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;
0016c: move-result-object v7
0016e: invoke-static v13, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00174: move-result-object v8
00176: iput-object v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$0 Ljava/lang/Object;
0017a: invoke-static v14, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00180: move-result-object v8
00182: iput-object v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$1 Ljava/lang/Object;
00186: invoke-static v6, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0018c: move-result-object v8
0018e: iput-object v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$2 Ljava/lang/Object;
00192: invoke-static v15, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00198: move-result-object v8
0019a: iput-object v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$3 Ljava/lang/Object;
0019e: invoke-static v2, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001a4: move-result-object v8
001a6: iput-object v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$4 Ljava/lang/Object;
001aa: invoke-static v7, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001b0: move-result-object v8
001b2: iput-object v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$5 Ljava/lang/Object;
001b6: const/4 v8, 0
001b8: iput v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->I$0 I
001bc: iput v5, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->label I
001c0: invoke-static v7, v0, Lcom/vk/pushme/network/util/CallHandlerKt;->await(Lokhttp3/Call; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
001c6: move-result-object v5
001c8: if-ne v5, v1, +003h
001cc: goto +49h
001ce: move-object v11, v5
001d0: move-object v5, v15
001d2: move-object v15, v11
001d4: check-cast v15, Lokhttp3/Response;
001d8: invoke-virtual v15, Lokhttp3/Response;->isSuccessful()Z
001de: move-result v9
001e0: if-eqz v9, +05ah
001e4: invoke-static Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;
001ea: move-result-object v9
001ec: new-instance v10, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;
001f0: invoke-direct v10, v15, v4, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;-><init>(Lokhttp3/Response; Lkotlin/coroutines/Continuation;)V
001f6: invoke-static v13, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001fc: move-result-object v13
001fe: iput-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$0 Ljava/lang/Object;
00202: invoke-static v14, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00208: move-result-object v13
0020a: iput-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$1 Ljava/lang/Object;
0020e: invoke-static v6, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00214: move-result-object v13
00216: iput-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$2 Ljava/lang/Object;
0021a: invoke-static v5, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00220: move-result-object v13
00222: iput-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$3 Ljava/lang/Object;
00226: invoke-static v2, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0022c: move-result-object v13
0022e: iput-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$4 Ljava/lang/Object;
00232: invoke-static v7, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00238: move-result-object v13
0023a: iput-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$5 Ljava/lang/Object;
0023e: invoke-static v15, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00244: move-result-object v13
00246: iput-object v13, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->L$6 Ljava/lang/Object;
0024a: iput v8, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->I$0 I
0024e: iput v3, v0, Lcom/vk/pushme/network/PushMeApiImpl$unsubscribeByToken$1;->label I
00252: invoke-static v9, v10, v0, Lkotlinx/coroutines/BuildersKt;->withContext(Lkotlin/coroutines/CoroutineContext; Lkotlin/jvm/functions/Function2; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00258: move-result-object v15
0025a: if-ne v15, v1, +003h
0025e: return-object v1
00260: check-cast v15, Ljava/lang/String;
00264: sget-object v13, Lkotlinx/serialization/json/Json;->Default Lkotlinx/serialization/json/Json$Default;
00268: invoke-virtual v13, Lkotlinx/serialization/json/Json;->getSerializersModule()Lkotlinx/serialization/modules/SerializersModule;
0026e: sget-object v14, Lcom/vk/pushme/network/model/response/UnsubscribeResponse;->Companion Lcom/vk/pushme/network/model/response/UnsubscribeResponse$Companion;
00272: invoke-virtual v14, Lcom/vk/pushme/network/model/response/UnsubscribeResponse$Companion;->serializer()Lkotlinx/serialization/KSerializer;
00278: move-result-object v14
0027a: check-cast v14, Lkotlinx/serialization/DeserializationStrategy;
0027e: invoke-virtual v13, v14, v15, Lkotlinx/serialization/json/Json;->decodeFromString(Lkotlinx/serialization/DeserializationStrategy; Ljava/lang/String;)Ljava/lang/Object;
00284: move-result-object v13
00286: check-cast v13, Lcom/vk/pushme/network/model/response/UnsubscribeResponse;
0028a: invoke-static v13, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
00290: move-result-object v13
00292: goto +23h
00294: new-instance v13, Lcom/vk/pushme/network/PushMeRequestException;
00298: invoke-virtual v15, Lokhttp3/Response;->message()Ljava/lang/String;
0029e: move-result-object v14
002a0: invoke-virtual v15, Lokhttp3/Response;->code()I
002a6: move-result v15
002a8: invoke-direct v13, v14, v15, Lcom/vk/pushme/network/PushMeRequestException;-><init>(Ljava/lang/String; I)V
002ae: sget-object v14, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
002b2: invoke-static v13, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
002b8: move-result-object v13
002ba: invoke-static v13, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
002c0: move-result-object v13
002c2: goto +bh
002c4: sget-object v14, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
002c8: invoke-static v13, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
002ce: move-result-object v13
002d0: invoke-static v13, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
002d6: move-result-object v13
002d8: invoke-static v13, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z
002de: move-result v14
002e0: if-eqz v14, +00ch
002e4: invoke-static v13, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
002ea: check-cast v13, Lcom/vk/pushme/network/model/response/UnsubscribeResponse;
002ee: invoke-direct v12, v13, Lcom/vk/pushme/network/PushMeApiImpl;->parseUnsubscribeResponseToResult(Lcom/vk/pushme/network/model/response/UnsubscribeResponse;)Lcom/vk/pushme/network/model/result/UnsubscribeResult;
002f4: move-result-object v13
002f6: goto +eh
002f8: new-instance v14, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;
002fc: invoke-static v13, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;
00302: move-result-object v13
00304: invoke-static v13, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V
0030a: invoke-direct v14, v13, Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;-><init>(Ljava/lang/Throwable;)V
00310: move-object v13, v14
00312: return-object v13
~~~


## Lru/mail/util/push/pusher/PushMeSDKPusherTransport;::unsubscribeAppByDeviceId (Ljava/lang/String; Lru/mail/util/push/PusherApplicationType;)V
Source APK member: Mail-15.107.0.148045.apk; DEX: classes16.dex

~~~smali-like
00000: const-string/jumbo v0, userIdentifier
00006: invoke-static v5, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000c: const-string v0, "application"
00010: invoke-static v6, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
00016: sget-object v0, Lru/mail/util/push/pusher/PushMeSDKPusherTransport;->LOG Lru/mail/util/log/Log;
0001a: invoke-virtual v6, Ljava/lang/Enum;->name()Ljava/lang/String;
00020: move-result-object v1
00022: new-instance v2, Ljava/lang/StringBuilder;
00026: invoke-direct v2, Ljava/lang/StringBuilder;-><init>()V
0002c: const-string v3, "Unsubscribing app "
00030: invoke-virtual v2, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00036: invoke-virtual v2, v1, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0003c: const-string v1, " by device ID for user "
00040: invoke-virtual v2, v1, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00046: invoke-virtual v2, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0004c: invoke-virtual v2, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00052: move-result-object v1
00054: invoke-interface v0, v1, Lru/mail/util/log/Log;->i(Ljava/lang/String;)V
0005a: invoke-virtual v4, Lru/mail/util/push/pusher/PushMeSDKPusherTransport;->getPusherAppNameProvider()Lru/mail/util/push/provider/PusherAppNameProvider;
00060: move-result-object v1
00062: invoke-interface v1, v6, Lru/mail/util/push/provider/PusherAppNameProvider;->getPusherAppName(Lru/mail/util/push/PusherApplicationType;)Ljava/lang/String;
00068: move-result-object v1
0006a: invoke-virtual v4, v5, v6, Lru/mail/util/push/pusher/BasePusherTransport;->getAccount(Ljava/lang/String; Lru/mail/util/push/PusherApplicationType;)Ljava/lang/String;
00070: move-result-object v2
00072: if-eqz v2, +036h
00076: invoke-static v2, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
0007c: move-result v3
0007e: if-eqz v3, +003h
00082: goto +2eh
00084: sget-object v6, Lcom/vk/pushme/PushMeSdk;->Companion Lcom/vk/pushme/PushMeSdk$Companion;
00088: invoke-virtual v6, v1, Lcom/vk/pushme/PushMeSdk$Companion;->getApp(Ljava/lang/String;)Lcom/vk/pushme/model/Application;
0008e: move-result-object v6
00090: invoke-interface v6, v2, Lcom/vk/pushme/model/Application;->unregisterAccount(Ljava/lang/String;)Lcom/vk/pushme/model/Request;
00096: move-result-object v6
00098: invoke-virtual v6, Lcom/vk/pushme/model/Request;->execute()Ljava/lang/Object;
0009e: move-result-object v6
000a0: check-cast v6, Lcom/vk/pushme/model/result/UnsubscribeResult;
000a4: new-instance v1, Ljava/lang/StringBuilder;
000a8: invoke-direct v1, Ljava/lang/StringBuilder;-><init>()V
000ae: const-string v2, "Unsubscribe result for "
000b2: invoke-virtual v1, v2, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
000b8: invoke-virtual v1, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
000be: const-string v5, " is "
000c2: invoke-virtual v1, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
000c8: invoke-virtual v1, v6, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;
000ce: invoke-virtual v1, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
000d4: move-result-object v5
000d6: invoke-interface v0, v5, Lru/mail/util/log/Log;->i(Ljava/lang/String;)V
000dc: return-void 
000de: new-instance v1, Ljava/lang/StringBuilder;
000e2: invoke-direct v1, Ljava/lang/StringBuilder;-><init>()V
000e8: const-string v2, "Could not get account for app "
000ec: invoke-virtual v1, v2, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
000f2: invoke-virtual v1, v6, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;
000f8: const-string v6, ", userIdentified = "
000fc: invoke-virtual v1, v6, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00102: invoke-virtual v1, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00108: invoke-virtual v1, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
0010e: move-result-object v5
00110: invoke-interface v0, v5, Lru/mail/util/log/Log;->w(Ljava/lang/String;)V
00116: return-void 
~~~
