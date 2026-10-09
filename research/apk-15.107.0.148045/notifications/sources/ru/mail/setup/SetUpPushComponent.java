package ru.mail.setup;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.format.DateUtils;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.push.service.HmsMsgService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.config.InitConfigurationRepoManager;
import ru.mail.config.section.PushAnalyticsConfigDto;
import ru.mail.config.section.PushMeSdkDto;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.core.di.AppCoreModuleEntryPoint;
import ru.mail.core.di.ConfigModuleEntryPoint;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.deviceinfo.di.DeviceInfoEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailapp.BuildConfig;
import ru.mail.ui.fragments.settings.DevSettingsUsedPushTransportsDelegate;
import ru.mail.util.BuildVariantHelper;
import ru.mail.util.log.Log;
import ru.mail.util.push.GcmAvailableStatusListener;
import ru.mail.util.push.ProcessPushMessageInServiceListener;
import ru.mail.util.push.PushFactory;
import ru.mail.util.push.PushFactoryCreatorKt;
import ru.mail.util.push.PushMessagesAnalyticsCollector;
import ru.mail.util.push.PushMessagesAnalyticsCollectorImpl;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushMessagesVkpnsAnalyticsCollector;
import ru.mail.util.push.PushTokenRegistrationListener;
import ru.mail.util.push.PushType;
import ru.mail.util.push.SendSettingsIfRegisteredListener;
import ru.mail.util.push.component.PushComponent;
import ru.mail.util.push.component.PushMeComponent;
import ru.mail.util.push.huawei.MailMessagingService;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushMessageReceivedNotifierImpl;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifierImpl;
import ru.mail.util.push.provider.impl.AdvertisingIdProviderImpl;
import ru.mail.util.push.vkpns.VkpnsAvailabilityChecker;
import ru.mail.util.push.vkpns.VkpnsHostResolver;
import ru.mail.util.push.vkpns.component.VkpnsComponent;
import ru.mail.util.push.vkpns.component.VkpnsComponentImpl;
import ru.mail.util.push.vkpns.filter.VkpnsPushDeduplicatorImpl;
import ru.mail.utils.GooglePlayServicesUtil;
import ru.mail.utils.HuaweiServicesUtil;
import ru.mail.utils.RuStoreUtil;
import ru.mail.utils.UtilExtensionsKt;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 ;2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001;B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002JD\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010 \u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J$\u0010'\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\u0010(\u001a\u0006\u0012\u0002\b\u00030)2\u0006\u0010*\u001a\u00020\"H\u0002J\u0010\u0010+\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J>\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u000203H\u0002J\u0016\u00104\u001a\u00020\u001c2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010H\u0002J\u0018\u00105\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u000eH\u0002J\u0018\u00107\u001a\u00020\"2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u000eH\u0002J\u0018\u00109\u001a\u00020:2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u0013H\u0002¨\u0006<"}, d2 = {"Lru/mail/setup/SetUpPushComponent;", "Lru/mail/setup/SetUpServiceLazy;", "Lru/mail/util/push/component/PushComponent;", "<init>", "()V", "onCreateServiceImpl", "app", "Landroid/app/Application;", "sendUserSessionParameters", "", "context", "Landroid/content/Context;", "pushTokens", "", "", "createPushFactories", "", "Lru/mail/util/push/PushFactory;", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "createPushTransports", "Lru/mail/util/push/PushMessagesTransport;", "factories", "tokenNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "messagesNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "isVkpnsPushShowEnabled", "", "isPushMeSdkInitEnabled", "configureHmsServices", "isHmsEnabledInDevSettings", "enableHuaweiServices", "getComponentState", "", "packageManagerUtil", "Lru/mail/utils/safeutils/PackageManagerUtil$RequestInitiator;", "name", "Landroid/content/ComponentName;", "setComponentState", "cls", "Ljava/lang/Class;", "newState", "isVkpnsServiceAvailable", "setPushAnalyticsListeners", "application", "vkpnsComponent", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "transports", "ruStoreSdkConfig", "pushAnalyticsConfig", "Lru/mail/config/section/PushAnalyticsConfigDto;", "hasVkpns", "isHostInstalled", "hostPackageName", "getAppVersionCode", "packageName", "createVkpnsPushDeduplicator", "Lru/mail/util/push/vkpns/filter/VkpnsPushDeduplicatorImpl;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSetUpPushComponent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SetUpPushComponent.kt\nru/mail/setup/SetUpPushComponent\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 CoroutineExceptionHandler.kt\nkotlinx/coroutines/CoroutineExceptionHandlerKt\n*L\n1#1,380:1\n1617#2,9:381\n1869#2:390\n1870#2:392\n1626#2:393\n1563#2:398\n1634#2,3:399\n1761#2,3:402\n1#3:391\n47#4,4:394\n*S KotlinDebug\n*F\n+ 1 SetUpPushComponent.kt\nru/mail/setup/SetUpPushComponent\n*L\n93#1:381,9\n93#1:390\n93#1:392\n93#1:393\n185#1:398\n185#1:399,3\n329#1:402,3\n93#1:391\n113#1:394,4\n*E\n"})
public final class SetUpPushComponent extends SetUpServiceLazy<PushComponent> {

    @NotNull
    private static final String NARROW_PUSH_DELIVERY_LOCK_KEY = "narrow_push_delivery_lock";

    @NotNull
    private static final String PUSH_PREFS = "push_analytic_prefs";

    @NotNull
    private static final String USER_SESSION_PARAMS_SENT_DATE = "USER_SESSION_PARAMS_SENT_DATE";
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SetUpPushComponent");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PushType.values().length];
            try {
                iArr[PushType.STUB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushType.GCM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: ru.mail.setup.SetUpPushComponent$sendUserSessionParameters$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.setup.SetUpPushComponent$sendUserSessionParameters$2", f = "SetUpPushComponent.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nSetUpPushComponent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SetUpPushComponent.kt\nru/mail/setup/SetUpPushComponent$sendUserSessionParameters$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 5 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,380:1\n1617#2,9:381\n1869#2:390\n1870#2:392\n1626#2:393\n1869#2:394\n1869#2:395\n1870#2:411\n1870#2:412\n1#3:391\n13230#4,3:396\n41#5,12:399\n*S KotlinDebug\n*F\n+ 1 SetUpPushComponent.kt\nru/mail/setup/SetUpPushComponent$sendUserSessionParameters$2\n*L\n117#1:381,9\n117#1:390\n117#1:392\n117#1:393\n121#1:394\n122#1:395\n122#1:411\n121#1:412\n117#1:391\n125#1:396,3\n132#1:399,12\n*E\n"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ SharedPreferences $preferences;
        final /* synthetic */ List<String> $pushTokens;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, List<String> list, SharedPreferences sharedPreferences, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$pushTokens = list;
            this.$preferences = sharedPreferences;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$context, this.$pushTokens, this.$preferences, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws NoSuchAlgorithmException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<MailboxProfile> accounts = CommonDataManager.from(this.$context).getAccounts();
            Intrinsics.checkNotNullExpressionValue(accounts, "getAccounts(...)");
            ArrayList<String> arrayList = new ArrayList();
            Iterator<T> it = accounts.iterator();
            while (it.hasNext()) {
                String login = ((MailboxProfile) it.next()).getLogin();
                if (login != null) {
                    arrayList.add(login);
                }
            }
            String advertisingId = new AdvertisingIdProviderImpl(this.$context).getAdvertisingId();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String androidId = DeviceInfoEntryPoint.INSTANCE.deviceIdProvider(this.$context).getAndroidId();
            List<String> list = this.$pushTokens;
            Context context = this.$context;
            SharedPreferences sharedPreferences = this.$preferences;
            for (String str : arrayList) {
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    byte[] bytes = ((String) it2.next()).getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    byte[] bArrDigest = messageDigest.digest(bytes);
                    Intrinsics.checkNotNull(bArrDigest);
                    String str2 = "";
                    for (byte b10 : bArrDigest) {
                        String str3 = String.format("%02x", Arrays.copyOf(new Object[]{Boxing.boxByte(b10)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                        str2 = str2 + str3;
                    }
                    MailAppDependencies.analyticsKt(context).sendUserSessionParameters(str, advertisingId, str2, androidId);
                    Intrinsics.checkNotNull(sharedPreferences);
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putLong(SetUpPushComponent.USER_SESSION_PARAMS_SENT_DATE, System.currentTimeMillis());
                    editorEdit.apply();
                }
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public SetUpPushComponent() {
        super(PushComponent.class);
    }

    private final void configureHmsServices(final Context context) {
        ((InitConfigurationRepoManager) Locator.INSTANCE.from(context).locate(InitConfigurationRepoManager.class)).addActualConfigurationListener(new InitConfigurationRepoManager.LoadActualConfigurationListener() { // from class: ru.mail.setup.p3
            @Override // ru.mail.config.InitConfigurationRepoManager.LoadActualConfigurationListener
            public final void onWaitingDone() {
                SetUpPushComponent.configureHmsServices$lambda$0(context, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void configureHmsServices$lambda$0(Context context, SetUpPushComponent setUpPushComponent) {
        ConfigurationWithRawData configuration = ConfigurationRepository.from(context).getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        if (!configuration.isHmsMessageServicesEnabled() || !setUpPushComponent.isHmsEnabledInDevSettings(context)) {
            LOG.d("Config prohibits using HMS services - HMS services weren't enabled");
        } else {
            LOG.d("Config allows using HMS services - enable HMS services");
            setUpPushComponent.enableHuaweiServices(context);
        }
    }

    private final Collection<PushFactory> createPushFactories(Context context, RuStoreSdkDto ruStoreConfig) {
        PushType pushType = BuildConfig.PUSH_TYPE;
        int i10 = pushType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[pushType.ordinal()];
        if (i10 == 1) {
            LOG.d("Use STUB push factory");
            return CollectionsKt.listOf(PushFactoryCreatorKt.createPushFactory(PushType.STUB));
        }
        if (i10 != 2) {
            LOG.d("No BuildConfig.PUSH_TYPE - use GCM factory");
            return CollectionsKt.listOf(PushFactoryCreatorKt.createPushFactory(PushType.GCM));
        }
        Log log = LOG;
        log.d("Check mobile services availability");
        List arrayList = new ArrayList();
        if (GooglePlayServicesUtil.isPlayServicesAvailable(context)) {
            log.d("GCM is available - use GCM push factory");
            arrayList.add(PushFactoryCreatorKt.createPushFactory(PushType.GCM));
        } else if (HuaweiServicesUtil.isHuaweiServicesAvailable(context)) {
            log.d("HMS is available - use HMS push factory");
            configureHmsServices(context);
            arrayList.add(PushFactoryCreatorKt.createPushFactory(PushType.HMS));
        }
        if (!arrayList.isEmpty() && isVkpnsServiceAvailable(ruStoreConfig)) {
            log.d("VKPNS is available - use VKPNS push factory");
            arrayList.add(PushFactoryCreatorKt.createPushFactory(PushType.VKPNS));
        }
        if (arrayList.isEmpty()) {
            log.d("No transport is available - use GCM push factory");
            arrayList = CollectionsKt.listOf(PushFactoryCreatorKt.createPushFactory(PushType.GCM));
        }
        return arrayList;
    }

    private final Collection<PushMessagesTransport> createPushTransports(Collection<? extends PushFactory> factories, Application app, PushTokenRefreshedNotifier tokenNotifier, PushMessageReceivedNotifier messagesNotifier, boolean isVkpnsPushShowEnabled, boolean isPushMeSdkInitEnabled) {
        Collection<? extends PushFactory> collection = factories;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            PushMessagesTransport pushMessagesTransportCreateTransport = ((PushFactory) it.next()).createTransport(app, tokenNotifier, messagesNotifier);
            pushMessagesTransportCreateTransport.addListener(new SendSettingsIfRegisteredListener(app));
            PushType pushType = pushMessagesTransportCreateTransport.getPushType();
            PushType pushType2 = PushType.VKPNS;
            if (pushType == pushType2 && isVkpnsPushShowEnabled) {
                pushMessagesTransportCreateTransport.addListener(new ProcessPushMessageInServiceListener(app));
            }
            if (pushMessagesTransportCreateTransport.getPushType() != pushType2) {
                pushMessagesTransportCreateTransport.addListener(new ProcessPushMessageInServiceListener(app));
                pushMessagesTransportCreateTransport.addListener(new PushTokenRegistrationListener(app, isPushMeSdkInitEnabled, pushMessagesTransportCreateTransport));
            }
            if (pushMessagesTransportCreateTransport.getPushType() == PushType.GCM) {
                pushMessagesTransportCreateTransport.addListener(new GcmAvailableStatusListener(app));
            }
            arrayList.add(pushMessagesTransportCreateTransport);
        }
        return arrayList;
    }

    private final VkpnsPushDeduplicatorImpl createVkpnsPushDeduplicator(Context context, RuStoreSdkDto ruStoreSdkConfig) {
        return new VkpnsPushDeduplicatorImpl(context, ruStoreSdkConfig.getShowPushConfig().getKeepReceivedPushesTillTheLast());
    }

    private final void enableHuaweiServices(Context context) {
        setComponentState(context, HmsMsgService.class, 1);
        setComponentState(context, MailMessagingService.class, 1);
    }

    private final int getAppVersionCode(Context context, String packageName) {
        PackageInfo packageInfoPerform = PackageManagerUtil.from(context).getPackageInfo(packageName, 0).onErrorReturn(null).perform();
        if (packageInfoPerform != null) {
            return packageInfoPerform.versionCode;
        }
        return 0;
    }

    private final int getComponentState(PackageManagerUtil.RequestInitiator packageManagerUtil, ComponentName name) {
        Integer numPerform = packageManagerUtil.getComponentEnabledSettings(name).onErrorReturn(0).perform();
        Intrinsics.checkNotNullExpressionValue(numPerform, "perform(...)");
        return numPerform.intValue();
    }

    private final boolean hasVkpns(Collection<? extends PushMessagesTransport> transports) {
        Collection<? extends PushMessagesTransport> collection = transports;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return false;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (((PushMessagesTransport) it.next()).getPushType() == PushType.VKPNS) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isHmsEnabledInDevSettings(Context context) {
        if (BuildVariantHelper.isRelease()) {
            return true;
        }
        return new DevSettingsUsedPushTransportsDelegate(context, null, 2, 0 == true ? 1 : 0).isPushTransportEnabledInDevSettings(PushType.HMS);
    }

    private final boolean isHostInstalled(Context context, String hostPackageName) {
        if (Intrinsics.areEqual(hostPackageName, "ru.vk.store")) {
            return RuStoreUtil.isRuStoreInstalled(context, 73L);
        }
        if (Intrinsics.areEqual(hostPackageName, context.getPackageName())) {
            return true;
        }
        Boolean boolPerform = PackageManagerUtil.from(context).isPackageExists(hostPackageName, 0).onErrorReturn(Boolean.FALSE).perform();
        Intrinsics.checkNotNull(boolPerform);
        return boolPerform.booleanValue();
    }

    private final boolean isVkpnsServiceAvailable(RuStoreSdkDto ruStoreConfig) {
        return new VkpnsAvailabilityChecker(ruStoreConfig).checkForAvailability().getIsAvailable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreateServiceImpl$lambda$0(Application application) {
        return AppCoreModuleEntryPoint.INSTANCE.configRetriever(application).getBoolean(NARROW_PUSH_DELIVERY_LOCK_KEY, true);
    }

    private final void sendUserSessionParameters(Context context, List<String> pushTokens) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PUSH_PREFS, 0);
        boolean zIsToday = DateUtils.isToday(sharedPreferences.getLong(USER_SESSION_PARAMS_SENT_DATE, 0L));
        Log log = LOG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("should send user session params :");
        sb2.append(!zIsToday);
        log.i(sb2.toString());
        if (zIsToday) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(new SetUpPushComponent$sendUserSessionParameters$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE))), null, null, new AnonymousClass2(context, pushTokens, sharedPreferences, null), 3, null);
    }

    private final void setComponentState(Context context, Class<?> cls, int newState) {
        ComponentName componentName = new ComponentName(context, cls);
        PackageManagerUtil.RequestInitiator requestInitiatorFrom = PackageManagerUtil.from(context);
        Intrinsics.checkNotNull(requestInitiatorFrom);
        if (getComponentState(requestInitiatorFrom, componentName) != newState) {
            requestInitiatorFrom.setComponentEnabledSetting(componentName, newState, 1).onErrorReturn(null).perform();
        }
    }

    private final void setPushAnalyticsListeners(Application application, PushMessageReceivedNotifier messagesNotifier, VkpnsComponent vkpnsComponent, Collection<? extends PushMessagesTransport> transports, RuStoreSdkDto ruStoreSdkConfig, PushAnalyticsConfigDto pushAnalyticsConfig) {
        boolean z10;
        PushMessagesAnalyticsCollector.VkpnsHostInfo vkpnsHostInfo;
        boolean z11 = false;
        if (hasVkpns(transports)) {
            z10 = true;
        } else {
            LOG.i("Do not collect push analytics as VKPNS transport is not found");
            z10 = false;
        }
        if (transports.size() < 2) {
            LOG.i("Do not collect push analytics as we have only " + transports.size() + " transports");
            z10 = false;
        }
        if (!ruStoreSdkConfig.isPushAnalyticsEnabled()) {
            LOG.i("Do not collect push analytics as it's disabled in config");
            z10 = false;
        }
        VkpnsHostResolver.HostInfo preferredHost = vkpnsComponent.getHostResolver().getPreferredHost();
        if (preferredHost == null || isHostInstalled(application, preferredHost.getPackageName())) {
            z11 = z10;
        } else {
            LOG.i("Do not collect push analytics as host is not installed");
        }
        if (preferredHost != null) {
            vkpnsHostInfo = new PushMessagesAnalyticsCollector.VkpnsHostInfo(preferredHost.getPackageName(), getAppVersionCode(application, preferredHost.getPackageName()), UtilExtensionsKt.isIgnoringBatteryOptimizations(application, preferredHost.getPackageName()));
        } else {
            vkpnsHostInfo = null;
        }
        PushMessagesAnalyticsCollector.VkpnsHostInfo vkpnsHostInfo2 = vkpnsHostInfo;
        MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(application);
        (z11 ? new PushMessagesVkpnsAnalyticsCollector(vkpnsComponent, messagesNotifier, mailAppAnalyticsAnalytics, vkpnsHostInfo2, pushAnalyticsConfig, ruStoreSdkConfig) : new PushMessagesAnalyticsCollectorImpl(vkpnsComponent, messagesNotifier, mailAppAnalyticsAnalytics, vkpnsHostInfo2, pushAnalyticsConfig, ruStoreSdkConfig)).startCollectAnalytics(transports);
    }

    @Override // ru.mail.setup.SetUpService
    @NotNull
    public PushComponent onCreateServiceImpl(@NotNull final Application app) {
        Intrinsics.checkNotNullParameter(app, "app");
        PushTokenRefreshedNotifierImpl pushTokenRefreshedNotifierImpl = new PushTokenRefreshedNotifierImpl();
        ConfigModuleEntryPoint.Companion companion = ConfigModuleEntryPoint.INSTANCE;
        RuStoreSdkDto ruStoreSdkDtoProvideRuStoreConfig = companion.provideRuStoreConfig(app);
        VkpnsComponentImpl vkpnsComponentImpl = new VkpnsComponentImpl(app, ruStoreSdkDtoProvideRuStoreConfig, companion.provideVkPnsHostConfig(app));
        boolean zIsShowPushEnabled = vkpnsComponentImpl.isShowPushEnabled();
        PushMeSdkDto pushMeSdkDtoProvidePushMeSdkConfig = companion.providePushMeSdkConfig(app);
        PushAnalyticsConfigDto pushAnalyticsConfigDtoProvidePushAnalyticsConfig = companion.providePushAnalyticsConfig(app);
        PushMessageReceivedNotifierImpl pushMessageReceivedNotifierImpl = new PushMessageReceivedNotifierImpl(zIsShowPushEnabled ? createVkpnsPushDeduplicator(app, ruStoreSdkDtoProvideRuStoreConfig) : null, new Function0() { // from class: ru.mail.setup.q3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SetUpPushComponent.onCreateServiceImpl$lambda$0(app));
            }
        });
        Collection<PushMessagesTransport> collectionCreatePushTransports = createPushTransports(createPushFactories(app, ruStoreSdkDtoProvideRuStoreConfig), app, pushTokenRefreshedNotifierImpl, pushMessageReceivedNotifierImpl, zIsShowPushEnabled, pushMeSdkDtoProvidePushMeSdkConfig.isInitSdkEnabled());
        setPushAnalyticsListeners(app, pushMessageReceivedNotifierImpl, vkpnsComponentImpl, collectionCreatePushTransports, ruStoreSdkDtoProvideRuStoreConfig, pushAnalyticsConfigDtoProvidePushAnalyticsConfig);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collectionCreatePushTransports.iterator();
        while (it.hasNext()) {
            String pushToken = ((PushMessagesTransport) it.next()).getPushToken();
            if (pushToken != null) {
                arrayList.add(pushToken);
            }
        }
        sendUserSessionParameters(app, arrayList);
        Context applicationContext = app.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return new PushMeComponent(applicationContext, pushMeSdkDtoProvidePushMeSdkConfig.isInitSdkEnabled(), ruStoreSdkDtoProvideRuStoreConfig, collectionCreatePushTransports, pushTokenRefreshedNotifierImpl, pushMessageReceivedNotifierImpl, vkpnsComponentImpl);
    }
}
