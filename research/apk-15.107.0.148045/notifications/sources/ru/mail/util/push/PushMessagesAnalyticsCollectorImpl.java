package ru.mail.util.push;

import androidx.annotation.CallSuper;
import androidx.annotation.WorkerThread;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.config.section.PushAnalyticsConfigDto;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.util.log.Log;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.vkpns.component.VkpnsComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0017\u0018\u0000 *2\u00020\u0001:\u0002)*B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0017J\u0016\u0010\u0017\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u001c\u0010\u0018\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0082@¢\u0006\u0002\u0010\u0019J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0082@¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0011H\u0005J$\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0#H\u0015J.\u0010$\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0#2\b\b\u0002\u0010%\u001a\u00020\u0011H\u0004J\b\u0010&\u001a\u00020\u001bH\u0004J\b\u0010'\u001a\u00020(H\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lru/mail/util/push/PushMessagesAnalyticsCollectorImpl;", "Lru/mail/util/push/PushMessagesAnalyticsCollector;", "vkpnsComponent", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "hostInfo", "Lru/mail/util/push/PushMessagesAnalyticsCollector$VkpnsHostInfo;", "pushAnalyticsConfig", "Lru/mail/config/section/PushAnalyticsConfigDto;", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "<init>", "(Lru/mail/util/push/vkpns/component/VkpnsComponent;Lru/mail/util/push/notifier/PushMessageReceivedNotifier;Lru/mail/analytics/MailAppAnalytics;Lru/mail/util/push/PushMessagesAnalyticsCollector$VkpnsHostInfo;Lru/mail/config/section/PushAnalyticsConfigDto;Lru/mail/config/section/RuStoreSdkDto;)V", "isVkpnsTokenDetected", "", "startCollectAnalytics", "", "transports", "", "Lru/mail/util/push/PushMessagesTransport;", "initMessageReceivedListeners", "sendInitAnalytics", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVkpnsAvailability", "", "config", "(Lru/mail/config/section/RuStoreSdkDto;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isVkpnsTokenExists", "onMessageReceived", "type", "Lru/mail/util/push/PushType;", "data", "", "sendMessageReceivedAnalytics", "isVkpnsAnalytics", "getHostPackageName", "getHostVersionCode", "", "MessageListener", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMessagesAnalyticsCollectorImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMessagesAnalyticsCollectorImpl.kt\nru/mail/util/push/PushMessagesAnalyticsCollectorImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,150:1\n1563#2:151\n1634#2,3:152\n1869#2,2:155\n*S KotlinDebug\n*F\n+ 1 PushMessagesAnalyticsCollectorImpl.kt\nru/mail/util/push/PushMessagesAnalyticsCollectorImpl\n*L\n43#1:151\n43#1:152,3\n44#1:155,2\n*E\n"})
public class PushMessagesAnalyticsCollectorImpl implements PushMessagesAnalyticsCollector {

    @NotNull
    private final MailAppAnalytics analytics;

    @Nullable
    private final PushMessagesAnalyticsCollector.VkpnsHostInfo hostInfo;
    private volatile boolean isVkpnsTokenDetected;

    @NotNull
    private final PushAnalyticsConfigDto pushAnalyticsConfig;

    @NotNull
    private final PushMessageReceivedNotifier pushMessageReceivedNotifier;

    @NotNull
    private final RuStoreSdkDto ruStoreConfig;

    @NotNull
    private final VkpnsComponent vkpnsComponent;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PushMessagesAnalyticsCollectorImpl");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/util/push/PushMessagesAnalyticsCollectorImpl$MessageListener;", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier$Listener;", "type", "Lru/mail/util/push/PushType;", "<init>", "(Lru/mail/util/push/PushMessagesAnalyticsCollectorImpl;Lru/mail/util/push/PushType;)V", "onMessageReceived", "", "data", "", "", "from", "pushMeSdkPushId", "", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;)V", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private final class MessageListener implements PushMessageReceivedNotifier.Listener {
        final /* synthetic */ PushMessagesAnalyticsCollectorImpl this$0;

        @NotNull
        private final PushType type;

        public MessageListener(@NotNull PushMessagesAnalyticsCollectorImpl pushMessagesAnalyticsCollectorImpl, PushType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.this$0 = pushMessagesAnalyticsCollectorImpl;
            this.type = type;
        }

        @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier.Listener
        public void onMessageReceived(@NotNull Map<String, String> data, @Nullable String from, @Nullable Long pushMeSdkPushId) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.this$0.onMessageReceived(this.type, data);
        }
    }

    /* JADX INFO: renamed from: ru.mail.util.push.PushMessagesAnalyticsCollectorImpl$getVkpnsAvailability$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.util.push.PushMessagesAnalyticsCollectorImpl$getVkpnsAvailability$2", f = "PushMessagesAnalyticsCollectorImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nPushMessagesAnalyticsCollectorImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMessagesAnalyticsCollectorImpl.kt\nru/mail/util/push/PushMessagesAnalyticsCollectorImpl$getVkpnsAvailability$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,150:1\n2746#2,3:151\n*S KotlinDebug\n*F\n+ 1 PushMessagesAnalyticsCollectorImpl.kt\nru/mail/util/push/PushMessagesAnalyticsCollectorImpl$getVkpnsAvailability$2\n*L\n68#1:151,3\n*E\n"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ RuStoreSdkDto $config;
        final /* synthetic */ Collection<PushMessagesTransport> $transports;
        int label;
        final /* synthetic */ PushMessagesAnalyticsCollectorImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(RuStoreSdkDto ruStoreSdkDto, Collection<? extends PushMessagesTransport> collection, PushMessagesAnalyticsCollectorImpl pushMessagesAnalyticsCollectorImpl, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$config = ruStoreSdkDto;
            this.$transports = collection;
            this.this$0 = pushMessagesAnalyticsCollectorImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$config, this.$transports, this.this$0, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String pushProjectId;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (!this.$config.isPushSdkEnabled() || (pushProjectId = this.$config.getPushProjectId()) == null || StringsKt.isBlank(pushProjectId)) {
                return "DISABLED_BY_CONFIG";
            }
            Collection<PushMessagesTransport> collection = this.$transports;
            if ((collection instanceof Collection) && collection.isEmpty()) {
                return "NO_PUSH_TRANSPORT";
            }
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                if (((PushMessagesTransport) it.next()).getPushType() == PushType.VKPNS) {
                    return !this.this$0.isVkpnsTokenExists() ? "NO_PUSH_TOKEN" : "AVAILABLE";
                }
            }
            return "NO_PUSH_TRANSPORT";
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.util.push.PushMessagesAnalyticsCollectorImpl$sendInitAnalytics$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.util.push.PushMessagesAnalyticsCollectorImpl", f = "PushMessagesAnalyticsCollectorImpl.kt", i = {0}, l = {54}, m = "sendInitAnalytics", n = {"transports"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PushMessagesAnalyticsCollectorImpl.this.sendInitAnalytics(null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.util.push.PushMessagesAnalyticsCollectorImpl$startCollectAnalytics$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.util.push.PushMessagesAnalyticsCollectorImpl$startCollectAnalytics$1", f = "PushMessagesAnalyticsCollectorImpl.kt", i = {}, l = {36}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C28631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Collection<PushMessagesTransport> $transports;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C28631(Collection<? extends PushMessagesTransport> collection, Continuation<? super C28631> continuation) {
            super(2, continuation);
            this.$transports = collection;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PushMessagesAnalyticsCollectorImpl.this.new C28631(this.$transports, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                PushMessagesAnalyticsCollectorImpl pushMessagesAnalyticsCollectorImpl = PushMessagesAnalyticsCollectorImpl.this;
                Collection<PushMessagesTransport> collection = this.$transports;
                this.label = 1;
                if (pushMessagesAnalyticsCollectorImpl.sendInitAnalytics(collection, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C28631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public PushMessagesAnalyticsCollectorImpl(@NotNull VkpnsComponent vkpnsComponent, @NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NotNull MailAppAnalytics analytics, @Nullable PushMessagesAnalyticsCollector.VkpnsHostInfo vkpnsHostInfo, @NotNull PushAnalyticsConfigDto pushAnalyticsConfig, @NotNull RuStoreSdkDto ruStoreConfig) {
        Intrinsics.checkNotNullParameter(vkpnsComponent, "vkpnsComponent");
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(pushAnalyticsConfig, "pushAnalyticsConfig");
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        this.vkpnsComponent = vkpnsComponent;
        this.pushMessageReceivedNotifier = pushMessageReceivedNotifier;
        this.analytics = analytics;
        this.hostInfo = vkpnsHostInfo;
        this.pushAnalyticsConfig = pushAnalyticsConfig;
        this.ruStoreConfig = ruStoreConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getVkpnsAvailability(RuStoreSdkDto ruStoreSdkDto, Collection<? extends PushMessagesTransport> collection, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(ruStoreSdkDto, collection, this, null), continuation);
    }

    private final void initMessageReceivedListeners(Collection<? extends PushMessagesTransport> transports) {
        Collection<? extends PushMessagesTransport> collection = transports;
        ArrayList<PushType> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((PushMessagesTransport) it.next()).getPushType());
        }
        for (PushType pushType : arrayList) {
            PushMessageReceivedNotifier pushMessageReceivedNotifier = this.pushMessageReceivedNotifier;
            Intrinsics.checkNotNull(pushType);
            pushMessageReceivedNotifier.addListener(new MessageListener(this, pushType), pushType, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object sendInitAnalytics(Collection<? extends PushMessagesTransport> collection, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i10 = anonymousClass1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i10 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object vkpnsAvailability = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(vkpnsAvailability);
            RuStoreSdkDto ruStoreSdkDto = this.ruStoreConfig;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(collection);
            anonymousClass1.label = 1;
            vkpnsAvailability = getVkpnsAvailability(ruStoreSdkDto, collection, anonymousClass1);
            if (vkpnsAvailability == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(vkpnsAvailability);
        }
        this.analytics.onPushTransportInitialized((String) vkpnsAvailability);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void sendMessageReceivedAnalytics$default(PushMessagesAnalyticsCollectorImpl pushMessagesAnalyticsCollectorImpl, PushType pushType, Map map, boolean z10, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendMessageReceivedAnalytics");
        }
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        pushMessagesAnalyticsCollectorImpl.sendMessageReceivedAnalytics(pushType, map, z10);
    }

    @NotNull
    protected final String getHostPackageName() {
        String packageName;
        PushMessagesAnalyticsCollector.VkpnsHostInfo vkpnsHostInfo = this.hostInfo;
        return (vkpnsHostInfo == null || (packageName = vkpnsHostInfo.getPackageName()) == null) ? "unknown" : packageName;
    }

    protected final int getHostVersionCode() {
        PushMessagesAnalyticsCollector.VkpnsHostInfo vkpnsHostInfo = this.hostInfo;
        if (vkpnsHostInfo != null) {
            return vkpnsHostInfo.getVersion();
        }
        return 0;
    }

    @WorkerThread
    protected final boolean isVkpnsTokenExists() {
        if (this.isVkpnsTokenDetected) {
            return true;
        }
        String pushTokenFromPushKit = this.vkpnsComponent.mo15888getPushKitWrapper().getPushTokenFromPushKit();
        if (pushTokenFromPushKit == null || StringsKt.isBlank(pushTokenFromPushKit)) {
            LOG.w("VKPNS token is null");
            return false;
        }
        LOG.i("VKPNS token has been detected, it's safe to collect analytics");
        this.isVkpnsTokenDetected = true;
        return true;
    }

    @CallSuper
    protected void onMessageReceived(@NotNull PushType type, @NotNull Map<String, String> data) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(data, "data");
        if (this.pushAnalyticsConfig.isEnabled() && this.pushAnalyticsConfig.isMessageReceivedEventEnabled()) {
            sendMessageReceivedAnalytics$default(this, type, data, false, 4, null);
        }
    }

    protected final void sendMessageReceivedAnalytics(@NotNull PushType type, @NotNull Map<String, String> data, boolean isVkpnsAnalytics) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(data, "data");
        String analyticsName = type.getAnalyticsName();
        String str = data.get("event");
        String str2 = str == null ? "unknown" : str;
        String str3 = data.get(PushProcessor.DATAKEY_PUSH_ID);
        String str4 = str3 == null ? "unknown" : str3;
        MailAppAnalytics mailAppAnalytics = this.analytics;
        String hostPackageName = getHostPackageName();
        int hostVersionCode = getHostVersionCode();
        PushMessagesAnalyticsCollector.VkpnsHostInfo vkpnsHostInfo = this.hostInfo;
        mailAppAnalytics.onPushMessageReceived(isVkpnsAnalytics, analyticsName, str2, str4, hostPackageName, hostVersionCode, vkpnsHostInfo != null ? Boolean.valueOf(vkpnsHostInfo.getHasBackgroundWorkPermission()) : null);
    }

    @Override // ru.mail.util.push.PushMessagesAnalyticsCollector
    @CallSuper
    public void startCollectAnalytics(@NotNull Collection<? extends PushMessagesTransport> transports) {
        Intrinsics.checkNotNullParameter(transports, "transports");
        initMessageReceivedListeners(transports);
        if (this.pushAnalyticsConfig.isEnabled() && this.pushAnalyticsConfig.isTransportInitializedEventEnabled()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null)), null, null, new C28631(transports, null), 3, null);
        }
    }
}
