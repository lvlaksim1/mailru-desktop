package com.vk.pushme.logic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00062\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcom/vk/pushme/logic/PendingAction;", "", "<init>", "()V", "Subscribe", "Unsubscribe", "Companion", "Lcom/vk/pushme/logic/PendingAction$Subscribe;", "Lcom/vk/pushme/logic/PendingAction$Unsubscribe;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PendingAction {

    @NotNull
    public static final String JSON_KEY_ACCOUNTS = "accounts";

    @NotNull
    public static final String JSON_KEY_APPLICATION = "application";

    @NotNull
    public static final String SUBSCRIBE_TYPE = "subscribe";

    @NotNull
    public static final String UNSUBSCRIBE_TYPE = "unsubscribe";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/vk/pushme/logic/PendingAction$Subscribe;", "Lcom/vk/pushme/logic/PendingAction;", "application", "", "<init>", "(Ljava/lang/String;)V", "getApplication", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Subscribe extends PendingAction {

        @NotNull
        private final String application;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Subscribe(@NotNull String application) {
            super(null);
            Intrinsics.checkNotNullParameter(application, "application");
            this.application = application;
            if (StringsKt.isBlank(application)) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }

        public static /* synthetic */ Subscribe copy$default(Subscribe subscribe, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = subscribe.application;
            }
            return subscribe.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getApplication() {
            return this.application;
        }

        @NotNull
        public final Subscribe copy(@NotNull String application) {
            Intrinsics.checkNotNullParameter(application, "application");
            return new Subscribe(application);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Subscribe) && Intrinsics.areEqual(this.application, ((Subscribe) other).application);
        }

        @NotNull
        public final String getApplication() {
            return this.application;
        }

        public int hashCode() {
            return this.application.hashCode();
        }

        @NotNull
        public String toString() {
            return "Subscribe(application=" + this.application + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/vk/pushme/logic/PendingAction$Unsubscribe;", "Lcom/vk/pushme/logic/PendingAction;", "accounts", "", "", "application", "<init>", "(Ljava/util/Set;Ljava/lang/String;)V", "getAccounts", "()Ljava/util/Set;", "getApplication", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPendingAction.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PendingAction.kt\ncom/vk/pushme/logic/PendingAction$Unsubscribe\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,27:1\n1740#2,3:28\n*S KotlinDebug\n*F\n+ 1 PendingAction.kt\ncom/vk/pushme/logic/PendingAction$Unsubscribe\n*L\n15#1:28,3\n*E\n"})
    public static final /* data */ class Unsubscribe extends PendingAction {

        @NotNull
        private final Set<String> accounts;

        @NotNull
        private final String application;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unsubscribe(@NotNull Set<String> accounts, @NotNull String application) {
            super(null);
            Intrinsics.checkNotNullParameter(accounts, "accounts");
            Intrinsics.checkNotNullParameter(application, "application");
            this.accounts = accounts;
            this.application = application;
            if (accounts.isEmpty()) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (StringsKt.isBlank(application)) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            Set<String> set = accounts;
            if ((set instanceof Collection) && set.isEmpty()) {
                return;
            }
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                if (StringsKt.isBlank((String) it.next())) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Unsubscribe copy$default(Unsubscribe unsubscribe, Set set, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                set = unsubscribe.accounts;
            }
            if ((i10 & 2) != 0) {
                str = unsubscribe.application;
            }
            return unsubscribe.copy(set, str);
        }

        @NotNull
        public final Set<String> component1() {
            return this.accounts;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getApplication() {
            return this.application;
        }

        @NotNull
        public final Unsubscribe copy(@NotNull Set<String> accounts, @NotNull String application) {
            Intrinsics.checkNotNullParameter(accounts, "accounts");
            Intrinsics.checkNotNullParameter(application, "application");
            return new Unsubscribe(accounts, application);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Unsubscribe)) {
                return false;
            }
            Unsubscribe unsubscribe = (Unsubscribe) other;
            return Intrinsics.areEqual(this.accounts, unsubscribe.accounts) && Intrinsics.areEqual(this.application, unsubscribe.application);
        }

        @NotNull
        public final Set<String> getAccounts() {
            return this.accounts;
        }

        @NotNull
        public final String getApplication() {
            return this.application;
        }

        public int hashCode() {
            return (this.accounts.hashCode() * 31) + this.application.hashCode();
        }

        @NotNull
        public String toString() {
            return "Unsubscribe(accounts=" + this.accounts + ", application=" + this.application + ")";
        }
    }

    public /* synthetic */ PendingAction(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PendingAction() {
    }
}
