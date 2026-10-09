package ru.mail.portal.app.adapter.notifications.settings;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import ru.mail.portal.app.adapter.TabAppAdapter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ$\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0016J\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0011\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0011\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0011\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0011\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0019\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0011H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/AsyncPortalNotificationsSettings;", "Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;", "portalNotificationsSettings", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "onInit", "Lkotlin/Function0;", "", "<init>", "(Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function0;)V", "init", "supportedApps", "", "", "allApps", "Lru/mail/portal/app/adapter/TabAppAdapter;", "areNotificationsEnabled", "", "appId", "areNotificationsEnabledInApplicationSettings", "areNotificationsEnabledInConfig", "areNotificationsEnabledInDeviceSettings", "getChannelId", "setNotificationsEnabledInApplicationSettings", "enabled", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AsyncPortalNotificationsSettings implements PortalNotificationsSettings {

    @NotNull
    private final CoroutineDispatcher dispatcher;

    @NotNull
    private final Function0<Unit> onInit;

    @NotNull
    private final PortalNotificationsSettings portalNotificationsSettings;

    /* JADX INFO: renamed from: ru.mail.portal.app.adapter.notifications.settings.AsyncPortalNotificationsSettings$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.app.adapter.notifications.settings.AsyncPortalNotificationsSettings$init$1", f = "AsyncPortalNotificationsSettings.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Collection<TabAppAdapter> $allApps;
        final /* synthetic */ Collection<String> $supportedApps;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Collection<String> collection, Collection<? extends TabAppAdapter> collection2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$supportedApps = collection;
            this.$allApps = collection2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AsyncPortalNotificationsSettings.this.new AnonymousClass1(this.$supportedApps, this.$allApps, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AsyncPortalNotificationsSettings.this.portalNotificationsSettings.init(this.$supportedApps, this.$allApps);
            AsyncPortalNotificationsSettings.this.onInit.invoke();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public AsyncPortalNotificationsSettings(@NotNull PortalNotificationsSettings portalNotificationsSettings, @NotNull CoroutineDispatcher dispatcher, @NotNull Function0<Unit> onInit) {
        Intrinsics.checkNotNullParameter(portalNotificationsSettings, "portalNotificationsSettings");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(onInit, "onInit");
        this.portalNotificationsSettings = portalNotificationsSettings;
        this.dispatcher = dispatcher;
        this.onInit = onInit;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabled(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return this.portalNotificationsSettings.areNotificationsEnabled(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInApplicationSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return this.portalNotificationsSettings.areNotificationsEnabledInApplicationSettings(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInConfig(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return this.portalNotificationsSettings.areNotificationsEnabledInConfig(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInDeviceSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return this.portalNotificationsSettings.areNotificationsEnabledInDeviceSettings(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    @NotNull
    public String getChannelId(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return this.portalNotificationsSettings.getChannelId(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void init(@NotNull Collection<String> supportedApps, @NotNull Collection<? extends TabAppAdapter> allApps) {
        Intrinsics.checkNotNullParameter(supportedApps, "supportedApps");
        Intrinsics.checkNotNullParameter(allApps, "allApps");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.dispatcher), null, null, new AnonymousClass1(supportedApps, allApps, null), 3, null);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void setNotificationsEnabledInApplicationSettings(@NotNull String appId, boolean enabled) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        this.portalNotificationsSettings.setNotificationsEnabledInApplicationSettings(appId, enabled);
    }

    public /* synthetic */ AsyncPortalNotificationsSettings(PortalNotificationsSettings portalNotificationsSettings, CoroutineDispatcher coroutineDispatcher, Function0 function0, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(portalNotificationsSettings, (i10 & 2) != 0 ? Dispatchers.getDefault() : coroutineDispatcher, function0);
    }
}
