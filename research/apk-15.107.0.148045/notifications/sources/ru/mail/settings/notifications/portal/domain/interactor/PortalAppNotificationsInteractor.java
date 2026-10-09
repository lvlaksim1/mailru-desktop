package ru.mail.settings.notifications.portal.domain.interactor;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.Configuration;
import ru.mail.portal.app.adapter.di.PortalApp;
import ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.settings.notifications.portal.data.TagModel;
import ru.mail.settings.notifications.portal.domain.PortalAppNotificationsRepository;
import ru.mail.settings.notifications.portal.domain.entity.PortalAppTags;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lru/mail/settings/notifications/portal/domain/interactor/PortalAppNotificationsInteractor;", "", "repository", "Lru/mail/settings/notifications/portal/domain/PortalAppNotificationsRepository;", "config", "Lru/mail/config/Configuration$Portal$Notifications;", "notificationsSettings", "Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;", "<init>", "(Lru/mail/settings/notifications/portal/domain/PortalAppNotificationsRepository;Lru/mail/config/Configuration$Portal$Notifications;Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;)V", "getNotificationsSettings", "()Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/mail/settings/notifications/portal/domain/entity/PortalAppTags;", "getState", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "initPortalTags", "", "appId", "", "toggleAllNotifications", "newChecked", "", "toggleTag", "tagModel", "Lru/mail/settings/notifications/portal/data/TagModel;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalAppNotificationsInteractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalAppNotificationsInteractor.kt\nru/mail/settings/notifications/portal/domain/interactor/PortalAppNotificationsInteractor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,64:1\n1563#2:65\n1634#2,3:66\n1563#2:69\n1634#2,3:70\n1563#2:73\n1634#2,3:74\n*S KotlinDebug\n*F\n+ 1 PortalAppNotificationsInteractor.kt\nru/mail/settings/notifications/portal/domain/interactor/PortalAppNotificationsInteractor\n*L\n27#1:65\n27#1:66,3\n42#1:69\n42#1:70,3\n55#1:73\n55#1:74,3\n*E\n"})
public final class PortalAppNotificationsInteractor {
    public static final int $stable = 8;

    @NotNull
    private final Configuration.Portal.Notifications config;

    @NotNull
    private final PortalNotificationsSettings notificationsSettings;

    @NotNull
    private final PortalAppNotificationsRepository repository;

    @NotNull
    private final MutableStateFlow<PortalAppTags> state;

    @Inject
    public PortalAppNotificationsInteractor(@NotNull PortalAppNotificationsRepository repository, @NotNull Configuration.Portal.Notifications config, @NotNull @PortalApp PortalNotificationsSettings notificationsSettings) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(notificationsSettings, "notificationsSettings");
        this.repository = repository;
        this.config = config;
        this.notificationsSettings = notificationsSettings;
        this.state = StateFlowKt.MutableStateFlow(null);
    }

    @NotNull
    public final PortalNotificationsSettings getNotificationsSettings() {
        return this.notificationsSettings;
    }

    @NotNull
    public final MutableStateFlow<PortalAppTags> getState() {
        return this.state;
    }

    public final void initPortalTags(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Set<Tag> tagsForApp = this.config.getTagsForApp(appId);
        Set<Integer> disabledTagIds = this.repository.getDisabledTagIds(appId);
        boolean zAreNotificationsEnabledInApplicationSettings = this.notificationsSettings.areNotificationsEnabledInApplicationSettings(appId);
        MutableStateFlow<PortalAppTags> mutableStateFlow = this.state;
        Set<Tag> set = tagsForApp;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        for (Tag tag : set) {
            arrayList.add(new TagModel(tag, !disabledTagIds.contains(Integer.valueOf(tag.getIdForPusher())), zAreNotificationsEnabledInApplicationSettings));
        }
        mutableStateFlow.setValue(new PortalAppTags(zAreNotificationsEnabledInApplicationSettings, arrayList));
    }

    public final void toggleAllNotifications(@NotNull String appId, boolean newChecked) {
        boolean z10;
        List listEmptyList;
        List<TagModel> tagsList;
        Intrinsics.checkNotNullParameter(appId, "appId");
        this.notificationsSettings.setNotificationsEnabledInApplicationSettings(appId, newChecked);
        MutableStateFlow<PortalAppTags> mutableStateFlow = this.state;
        PortalAppTags value = mutableStateFlow.getValue();
        if (value == null || (tagsList = value.getTagsList()) == null) {
            z10 = newChecked;
            listEmptyList = CollectionsKt.emptyList();
        } else {
            List<TagModel> list = tagsList;
            listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                boolean z11 = newChecked;
                listEmptyList.add(TagModel.copy$default((TagModel) it.next(), null, false, z11, 3, null));
                newChecked = z11;
            }
            z10 = newChecked;
        }
        mutableStateFlow.setValue(new PortalAppTags(z10, listEmptyList));
    }

    public final void toggleTag(@NotNull String appId, @NotNull TagModel tagModel, boolean newChecked) {
        List listEmptyList;
        List<TagModel> tagsList;
        boolean z10;
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(tagModel, "tagModel");
        this.repository.setTagEnabled(appId, tagModel.getTag().getIdForPusher(), newChecked);
        MutableStateFlow<PortalAppTags> mutableStateFlow = this.state;
        PortalAppTags value = mutableStateFlow.getValue();
        boolean mainSwitchChecked = value != null ? value.getMainSwitchChecked() : false;
        PortalAppTags value2 = this.state.getValue();
        if (value2 == null || (tagsList = value2.getTagsList()) == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            List<TagModel> list = tagsList;
            listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (TagModel tagModelCopy$default : list) {
                if (Intrinsics.areEqual(tagModelCopy$default.getTag(), tagModel.getTag())) {
                    z10 = newChecked;
                    tagModelCopy$default = TagModel.copy$default(tagModelCopy$default, null, z10, true, 1, null);
                } else {
                    z10 = newChecked;
                }
                listEmptyList.add(tagModelCopy$default);
                newChecked = z10;
            }
        }
        mutableStateFlow.setValue(new PortalAppTags(mainSwitchChecked, listEmptyList));
    }
}
