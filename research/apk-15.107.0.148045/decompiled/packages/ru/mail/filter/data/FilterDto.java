package ru.mail.filter.data;

import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.news_feed.util.pulsedeeplinks.ActionParser;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003JA\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lru/mail/filter/data/FilterDto;", "", "id", "", "enabled", "", "applyToSpam", "conditions", "", "Lru/mail/filter/data/ConditionDto;", ActionParser.KEY_MULTIPLE_ACTIONS, "Lru/mail/filter/data/ActionsDto;", "<init>", "(Ljava/lang/String;ZZLjava/util/List;Lru/mail/filter/data/ActionsDto;)V", "getId", "()Ljava/lang/String;", "getEnabled", "()Z", "getApplyToSpam", "getConditions", "()Ljava/util/List;", "getActions", "()Lru/mail/filter/data/ActionsDto;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class FilterDto {

    @NotNull
    private final ActionsDto actions;
    private final boolean applyToSpam;

    @NotNull
    private final List<ConditionDto> conditions;
    private final boolean enabled;

    @NotNull
    private final String id;

    public FilterDto(@NotNull String id2, boolean z10, boolean z11, @NotNull List<ConditionDto> conditions, @NotNull ActionsDto actions) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(conditions, "conditions");
        Intrinsics.checkNotNullParameter(actions, "actions");
        this.id = id2;
        this.enabled = z10;
        this.applyToSpam = z11;
        this.conditions = conditions;
        this.actions = actions;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FilterDto copy$default(FilterDto filterDto, String str, boolean z10, boolean z11, List list, ActionsDto actionsDto, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = filterDto.id;
        }
        if ((i10 & 2) != 0) {
            z10 = filterDto.enabled;
        }
        if ((i10 & 4) != 0) {
            z11 = filterDto.applyToSpam;
        }
        if ((i10 & 8) != 0) {
            list = filterDto.conditions;
        }
        if ((i10 & 16) != 0) {
            actionsDto = filterDto.actions;
        }
        ActionsDto actionsDto2 = actionsDto;
        boolean z12 = z11;
        return filterDto.copy(str, z10, z12, list, actionsDto2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getApplyToSpam() {
        return this.applyToSpam;
    }

    @NotNull
    public final List<ConditionDto> component4() {
        return this.conditions;
    }

    @NotNull
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ActionsDto getActions() {
        return this.actions;
    }

    @NotNull
    public final FilterDto copy(@NotNull String id2, boolean enabled, boolean applyToSpam, @NotNull List<ConditionDto> conditions, @NotNull ActionsDto actions) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(conditions, "conditions");
        Intrinsics.checkNotNullParameter(actions, "actions");
        return new FilterDto(id2, enabled, applyToSpam, conditions, actions);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterDto)) {
            return false;
        }
        FilterDto filterDto = (FilterDto) other;
        return Intrinsics.areEqual(this.id, filterDto.id) && this.enabled == filterDto.enabled && this.applyToSpam == filterDto.applyToSpam && Intrinsics.areEqual(this.conditions, filterDto.conditions) && Intrinsics.areEqual(this.actions, filterDto.actions);
    }

    @NotNull
    public final ActionsDto getActions() {
        return this.actions;
    }

    public final boolean getApplyToSpam() {
        return this.applyToSpam;
    }

    @NotNull
    public final List<ConditionDto> getConditions() {
        return this.conditions;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + Boolean.hashCode(this.enabled)) * 31) + Boolean.hashCode(this.applyToSpam)) * 31) + this.conditions.hashCode()) * 31) + this.actions.hashCode();
    }

    @NotNull
    public String toString() {
        return "FilterDto(id=" + this.id + ", enabled=" + this.enabled + ", applyToSpam=" + this.applyToSpam + ", conditions=" + this.conditions + ", actions=" + this.actions + ")";
    }
}
