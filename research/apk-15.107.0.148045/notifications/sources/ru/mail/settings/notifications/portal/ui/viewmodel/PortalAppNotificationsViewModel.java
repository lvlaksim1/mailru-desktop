package ru.mail.settings.notifications.portal.ui.viewmodel;

import androidx.compose.runtime.internal.StabilityInferred;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.mail.march.concurrent.ViewModelDispatcher;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.settings.notifications.portal.data.TagModel;
import ru.mail.settings.notifications.portal.di.PortalAppNotificationsViewModelAssistedFactory;
import ru.mail.settings.notifications.portal.domain.entity.PortalAppTags;
import ru.mail.settings.notifications.portal.domain.interactor.PortalAppNotificationsInteractor;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 #2\u00020\u0001:\u0003!\"#B/\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006$"}, d2 = {"Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel;", "Landroidx/lifecycle/ViewModel;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "appLogger", "Lru/mail/util/log/Logger;", "appId", "", "interactor", "Lru/mail/settings/notifications/portal/domain/interactor/PortalAppNotificationsInteractor;", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lru/mail/util/log/Logger;Ljava/lang/String;Lru/mail/settings/notifications/portal/domain/interactor/PortalAppNotificationsInteractor;)V", "getAppId", "()Ljava/lang/String;", "logger", "state", "Lkotlinx/coroutines/flow/StateFlow;", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$State;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Lkotlin/Function1;", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event;", "", "getHandler", "()Lkotlin/jvm/functions/Function1;", "onMainTagSwitchClicked", "newEnabled", "", "onTagSwitchClicked", "tagModel", "Lru/mail/settings/notifications/portal/data/TagModel;", "newChecked", "State", "Event", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalAppNotificationsViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalAppNotificationsViewModel.kt\nru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,89:1\n49#2:90\n51#2:94\n46#3:91\n51#3:93\n105#4:92\n*S KotlinDebug\n*F\n+ 1 PortalAppNotificationsViewModel.kt\nru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel\n*L\n36#1:90\n36#1:94\n36#1:91\n36#1:93\n36#1:92\n*E\n"})
public final class PortalAppNotificationsViewModel extends ViewModel {

    @NotNull
    private final String appId;

    @NotNull
    private final CoroutineDispatcher dispatcher;

    @NotNull
    private final Function1<Event, Unit> handler;

    @NotNull
    private final PortalAppNotificationsInteractor interactor;

    @NotNull
    private final Logger logger;

    @NotNull
    private final StateFlow<State> state;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Companion;", "", "<init>", "()V", "providesFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "assistedFactory", "Lru/mail/settings/notifications/portal/di/PortalAppNotificationsViewModelAssistedFactory;", "appId", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ViewModelProvider.Factory providesFactory(@NotNull final PortalAppNotificationsViewModelAssistedFactory assistedFactory, @NotNull final String appId) {
            Intrinsics.checkNotNullParameter(assistedFactory, "assistedFactory");
            Intrinsics.checkNotNullParameter(appId, "appId");
            return new ViewModelProvider.Factory() { // from class: ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel$Companion$providesFactory$1
                @Override // androidx.lifecycle.ViewModelProvider.Factory
                public /* bridge */ <T extends ViewModel> T create(Class<T> cls, CreationExtras creationExtras) {
                    return (T) super.create(cls, creationExtras);
                }

                @Override // androidx.lifecycle.ViewModelProvider.Factory
                public /* bridge */ <T extends ViewModel> T create(KClass<T> kClass, CreationExtras creationExtras) {
                    return (T) super.create(kClass, creationExtras);
                }

                @Override // androidx.lifecycle.ViewModelProvider.Factory
                public <T extends ViewModel> T create(Class<T> modelClass) {
                    Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                    PortalAppNotificationsViewModel portalAppNotificationsViewModelCreate = assistedFactory.create(appId);
                    Intrinsics.checkNotNull(portalAppNotificationsViewModelCreate, "null cannot be cast to non-null type T of ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel.Companion.providesFactory.<no name provided>.create");
                    return portalAppNotificationsViewModelCreate;
                }
            };
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event;", "", "MainTagSwitchClicked", "TagSwitchClicked", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event$MainTagSwitchClicked;", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event$TagSwitchClicked;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Event {

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event$MainTagSwitchClicked;", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event;", "newEnabled", "", "<init>", "(Z)V", "getNewEnabled", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class MainTagSwitchClicked implements Event {
            public static final int $stable = 0;
            private final boolean newEnabled;

            public MainTagSwitchClicked(boolean z10) {
                this.newEnabled = z10;
            }

            public static /* synthetic */ MainTagSwitchClicked copy$default(MainTagSwitchClicked mainTagSwitchClicked, boolean z10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    z10 = mainTagSwitchClicked.newEnabled;
                }
                return mainTagSwitchClicked.copy(z10);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final boolean getNewEnabled() {
                return this.newEnabled;
            }

            @NotNull
            public final MainTagSwitchClicked copy(boolean newEnabled) {
                return new MainTagSwitchClicked(newEnabled);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof MainTagSwitchClicked) && this.newEnabled == ((MainTagSwitchClicked) other).newEnabled;
            }

            public final boolean getNewEnabled() {
                return this.newEnabled;
            }

            public int hashCode() {
                return Boolean.hashCode(this.newEnabled);
            }

            @NotNull
            public String toString() {
                return "MainTagSwitchClicked(newEnabled=" + this.newEnabled + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event$TagSwitchClicked;", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$Event;", "tagModel", "Lru/mail/settings/notifications/portal/data/TagModel;", "newChecked", "", "<init>", "(Lru/mail/settings/notifications/portal/data/TagModel;Z)V", "getTagModel", "()Lru/mail/settings/notifications/portal/data/TagModel;", "getNewChecked", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class TagSwitchClicked implements Event {
            public static final int $stable = 8;
            private final boolean newChecked;

            @NotNull
            private final TagModel tagModel;

            public TagSwitchClicked(@NotNull TagModel tagModel, boolean z10) {
                Intrinsics.checkNotNullParameter(tagModel, "tagModel");
                this.tagModel = tagModel;
                this.newChecked = z10;
            }

            public static /* synthetic */ TagSwitchClicked copy$default(TagSwitchClicked tagSwitchClicked, TagModel tagModel, boolean z10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    tagModel = tagSwitchClicked.tagModel;
                }
                if ((i10 & 2) != 0) {
                    z10 = tagSwitchClicked.newChecked;
                }
                return tagSwitchClicked.copy(tagModel, z10);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final TagModel getTagModel() {
                return this.tagModel;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final boolean getNewChecked() {
                return this.newChecked;
            }

            @NotNull
            public final TagSwitchClicked copy(@NotNull TagModel tagModel, boolean newChecked) {
                Intrinsics.checkNotNullParameter(tagModel, "tagModel");
                return new TagSwitchClicked(tagModel, newChecked);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof TagSwitchClicked)) {
                    return false;
                }
                TagSwitchClicked tagSwitchClicked = (TagSwitchClicked) other;
                return Intrinsics.areEqual(this.tagModel, tagSwitchClicked.tagModel) && this.newChecked == tagSwitchClicked.newChecked;
            }

            public final boolean getNewChecked() {
                return this.newChecked;
            }

            @NotNull
            public final TagModel getTagModel() {
                return this.tagModel;
            }

            public int hashCode() {
                return (this.tagModel.hashCode() * 31) + Boolean.hashCode(this.newChecked);
            }

            @NotNull
            public String toString() {
                return "TagSwitchClicked(tagModel=" + this.tagModel + ", newChecked=" + this.newChecked + ")";
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$State;", "", "isMainSwitchChecked", "", "tagsList", "", "Lru/mail/settings/notifications/portal/data/TagModel;", "<init>", "(ZLjava/util/List;)V", "()Z", "getTagsList", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class State {
        public static final int $stable = 8;
        private final boolean isMainSwitchChecked;

        @NotNull
        private final List<TagModel> tagsList;

        public State(boolean z10, @NotNull List<TagModel> tagsList) {
            Intrinsics.checkNotNullParameter(tagsList, "tagsList");
            this.isMainSwitchChecked = z10;
            this.tagsList = tagsList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ State copy$default(State state, boolean z10, List list, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = state.isMainSwitchChecked;
            }
            if ((i10 & 2) != 0) {
                list = state.tagsList;
            }
            return state.copy(z10, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsMainSwitchChecked() {
            return this.isMainSwitchChecked;
        }

        @NotNull
        public final List<TagModel> component2() {
            return this.tagsList;
        }

        @NotNull
        public final State copy(boolean isMainSwitchChecked, @NotNull List<TagModel> tagsList) {
            Intrinsics.checkNotNullParameter(tagsList, "tagsList");
            return new State(isMainSwitchChecked, tagsList);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof State)) {
                return false;
            }
            State state = (State) other;
            return this.isMainSwitchChecked == state.isMainSwitchChecked && Intrinsics.areEqual(this.tagsList, state.tagsList);
        }

        @NotNull
        public final List<TagModel> getTagsList() {
            return this.tagsList;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.isMainSwitchChecked) * 31) + this.tagsList.hashCode();
        }

        public final boolean isMainSwitchChecked() {
            return this.isMainSwitchChecked;
        }

        @NotNull
        public String toString() {
            return "State(isMainSwitchChecked=" + this.isMainSwitchChecked + ", tagsList=" + this.tagsList + ")";
        }
    }

    @AssistedInject
    public PortalAppNotificationsViewModel(@ViewModelDispatcher @NotNull CoroutineDispatcher dispatcher, @AppLogger @NotNull Logger appLogger, @Assisted @NotNull String appId, @NotNull PortalAppNotificationsInteractor interactor) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(interactor, "interactor");
        this.dispatcher = dispatcher;
        this.appId = appId;
        this.interactor = interactor;
        Logger loggerCreateLogger = appLogger.createLogger("PortalAppNotificationsViewModel");
        this.logger = loggerCreateLogger;
        interactor.initPortalTags(appId);
        final MutableStateFlow<PortalAppTags> state = interactor.getState();
        this.state = ExtensionsKt.stateFlow(this, dispatcher, loggerCreateLogger, (Object) null, new Flow<State>() { // from class: ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel$special$$inlined$map$1

            /* JADX INFO: renamed from: ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel$special$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 PortalAppNotificationsViewModel.kt\nru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel\n*L\n1#1,49:1\n50#2:50\n37#3,4:51\n*E\n"})
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel$special$$inlined$map$1$2", f = "PortalAppNotificationsViewModel.kt", i = {0, 0, 0, 0, 0}, l = {50}, m = "emit", n = {"value", "$completion", "value", "$this$map_u24lambda_u245", "$i$a$-unsafeTransform-FlowKt__TransformKt$map$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    List<TagModel> listEmptyList;
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
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i11 = anonymousClass1.label;
                    if (i11 == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        PortalAppTags portalAppTags = (PortalAppTags) obj;
                        boolean mainSwitchChecked = portalAppTags != null ? portalAppTags.getMainSwitchChecked() : false;
                        if (portalAppTags == null || (listEmptyList = portalAppTags.getTagsList()) == null) {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        PortalAppNotificationsViewModel.State state = new PortalAppNotificationsViewModel.State(mainSwitchChecked, listEmptyList);
                        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                        anonymousClass1.I$0 = 0;
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(state, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i11 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super PortalAppNotificationsViewModel.State> flowCollector, Continuation continuation) {
                Object objCollect = state.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        });
        this.handler = ExtensionsKt.eventHandler$default(ViewModelKt.getViewModelScope(this), dispatcher, loggerCreateLogger, 0L, new Function1() { // from class: ru.mail.settings.notifications.portal.ui.viewmodel.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PortalAppNotificationsViewModel.handler$lambda$0(this.f97201a, (PortalAppNotificationsViewModel.Event) obj);
            }
        }, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handler$lambda$0(PortalAppNotificationsViewModel portalAppNotificationsViewModel, Event it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it instanceof Event.MainTagSwitchClicked) {
            portalAppNotificationsViewModel.onMainTagSwitchClicked(((Event.MainTagSwitchClicked) it).getNewEnabled());
        } else {
            if (!(it instanceof Event.TagSwitchClicked)) {
                throw new NoWhenBranchMatchedException();
            }
            Event.TagSwitchClicked tagSwitchClicked = (Event.TagSwitchClicked) it;
            portalAppNotificationsViewModel.onTagSwitchClicked(tagSwitchClicked.getTagModel(), tagSwitchClicked.getNewChecked());
        }
        return Unit.INSTANCE;
    }

    private final void onMainTagSwitchClicked(boolean newEnabled) {
        this.interactor.toggleAllNotifications(this.appId, newEnabled);
    }

    private final void onTagSwitchClicked(TagModel tagModel, boolean newChecked) {
        this.interactor.toggleTag(this.appId, tagModel, newChecked);
    }

    @NotNull
    public final String getAppId() {
        return this.appId;
    }

    @NotNull
    public final Function1<Event, Unit> getHandler() {
        return this.handler;
    }

    @NotNull
    public final StateFlow<State> getState() {
        return this.state;
    }
}
