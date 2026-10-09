package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.SmartReply;
import ru.mail.logic.content.MailItemTransactionCategory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\b'\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"Lru/mail/util/push/Entity;", "", "<init>", "()V", "NotificationData", "ThreadIdAndSubject", "CountAndSender", "ThreadIdAndSubjectList", "ThreadIdCountSender", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Entity {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/util/push/Entity$CountAndSender;", "", "count", "", "sender", "", "<init>", "(ILjava/lang/CharSequence;)V", "getCount", "()I", "getSender", "()Ljava/lang/CharSequence;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CountAndSender {
        public static final int $stable = 8;
        private final int count;

        @NotNull
        private final CharSequence sender;

        public CountAndSender(int i10, @NotNull CharSequence sender) {
            Intrinsics.checkNotNullParameter(sender, "sender");
            this.count = i10;
            this.sender = sender;
        }

        public static /* synthetic */ CountAndSender copy$default(CountAndSender countAndSender, int i10, CharSequence charSequence, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = countAndSender.count;
            }
            if ((i11 & 2) != 0) {
                charSequence = countAndSender.sender;
            }
            return countAndSender.copy(i10, charSequence);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final CharSequence getSender() {
            return this.sender;
        }

        @NotNull
        public final CountAndSender copy(int count, @NotNull CharSequence sender) {
            Intrinsics.checkNotNullParameter(sender, "sender");
            return new CountAndSender(count, sender);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CountAndSender)) {
                return false;
            }
            CountAndSender countAndSender = (CountAndSender) other;
            return this.count == countAndSender.count && Intrinsics.areEqual(this.sender, countAndSender.sender);
        }

        public final int getCount() {
            return this.count;
        }

        @NotNull
        public final CharSequence getSender() {
            return this.sender;
        }

        public int hashCode() {
            return (Integer.hashCode(this.count) * 31) + this.sender.hashCode();
        }

        @NotNull
        public String toString() {
            return "CountAndSender(count=" + this.count + ", sender=" + ((Object) this.sender) + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010\u001d\u001a\u00020\nJ\u0006\u0010\u001e\u001a\u00020\nJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003J\t\u0010'\u001a\u00020\fHÆ\u0003JC\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010)\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u001aHÖ\u0001J\t\u0010,\u001a\u00020-HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006."}, d2 = {"Lru/mail/util/push/Entity$NotificationData;", "", "folder", "Lru/mail/data/entities/MailBoxFolder;", "push", "Lru/mail/util/push/NewMailPush;", "smartReplies", "", "Lru/mail/data/entities/SmartReply;", "hasStageSmartReply", "", "category", "Lru/mail/logic/content/MailItemTransactionCategory;", "<init>", "(Lru/mail/data/entities/MailBoxFolder;Lru/mail/util/push/NewMailPush;Ljava/util/List;ZLru/mail/logic/content/MailItemTransactionCategory;)V", "getFolder", "()Lru/mail/data/entities/MailBoxFolder;", "getPush", "()Lru/mail/util/push/NewMailPush;", "getSmartReplies", "()Ljava/util/List;", "getHasStageSmartReply", "()Z", "getCategory", "()Lru/mail/logic/content/MailItemTransactionCategory;", "notificationId", "", "getNotificationId", "()I", "hasSmartReplies", "isDefaultSmartReplies", "asMeta", "Lru/mail/util/push/NotificationMeta;", "pushMessageType", "Lru/mail/util/push/PushMessageType;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nEntity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Entity.kt\nru/mail/util/push/Entity$NotificationData\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,48:1\n1761#2,3:49\n*S KotlinDebug\n*F\n+ 1 Entity.kt\nru/mail/util/push/Entity$NotificationData\n*L\n21#1:49,3\n*E\n"})
    public static final /* data */ class NotificationData {
        public static final int $stable = 8;

        @NotNull
        private final MailItemTransactionCategory category;

        @Nullable
        private final MailBoxFolder folder;
        private final boolean hasStageSmartReply;

        @NotNull
        private final NewMailPush push;

        @NotNull
        private final List<SmartReply> smartReplies;

        public NotificationData(@Nullable MailBoxFolder mailBoxFolder, @NotNull NewMailPush push, @NotNull List<SmartReply> smartReplies, boolean z10, @NotNull MailItemTransactionCategory category) {
            Intrinsics.checkNotNullParameter(push, "push");
            Intrinsics.checkNotNullParameter(smartReplies, "smartReplies");
            Intrinsics.checkNotNullParameter(category, "category");
            this.folder = mailBoxFolder;
            this.push = push;
            this.smartReplies = smartReplies;
            this.hasStageSmartReply = z10;
            this.category = category;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ NotificationData copy$default(NotificationData notificationData, MailBoxFolder mailBoxFolder, NewMailPush newMailPush, List list, boolean z10, MailItemTransactionCategory mailItemTransactionCategory, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                mailBoxFolder = notificationData.folder;
            }
            if ((i10 & 2) != 0) {
                newMailPush = notificationData.push;
            }
            if ((i10 & 4) != 0) {
                list = notificationData.smartReplies;
            }
            if ((i10 & 8) != 0) {
                z10 = notificationData.hasStageSmartReply;
            }
            if ((i10 & 16) != 0) {
                mailItemTransactionCategory = notificationData.category;
            }
            MailItemTransactionCategory mailItemTransactionCategory2 = mailItemTransactionCategory;
            List list2 = list;
            return notificationData.copy(mailBoxFolder, newMailPush, list2, z10, mailItemTransactionCategory2);
        }

        @NotNull
        public final NotificationMeta asMeta(@NotNull PushMessageType pushMessageType) {
            Intrinsics.checkNotNullParameter(pushMessageType, "pushMessageType");
            return new NotificationMeta(this.category, pushMessageType, this.hasStageSmartReply, isDefaultSmartReplies(), !this.smartReplies.isEmpty(), false, 32, null);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MailBoxFolder getFolder() {
            return this.folder;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final NewMailPush getPush() {
            return this.push;
        }

        @NotNull
        public final List<SmartReply> component3() {
            return this.smartReplies;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getHasStageSmartReply() {
            return this.hasStageSmartReply;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final MailItemTransactionCategory getCategory() {
            return this.category;
        }

        @NotNull
        public final NotificationData copy(@Nullable MailBoxFolder folder, @NotNull NewMailPush push, @NotNull List<SmartReply> smartReplies, boolean hasStageSmartReply, @NotNull MailItemTransactionCategory category) {
            Intrinsics.checkNotNullParameter(push, "push");
            Intrinsics.checkNotNullParameter(smartReplies, "smartReplies");
            Intrinsics.checkNotNullParameter(category, "category");
            return new NotificationData(folder, push, smartReplies, hasStageSmartReply, category);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NotificationData)) {
                return false;
            }
            NotificationData notificationData = (NotificationData) other;
            return Intrinsics.areEqual(this.folder, notificationData.folder) && Intrinsics.areEqual(this.push, notificationData.push) && Intrinsics.areEqual(this.smartReplies, notificationData.smartReplies) && this.hasStageSmartReply == notificationData.hasStageSmartReply && this.category == notificationData.category;
        }

        @NotNull
        public final MailItemTransactionCategory getCategory() {
            return this.category;
        }

        @Nullable
        public final MailBoxFolder getFolder() {
            return this.folder;
        }

        public final boolean getHasStageSmartReply() {
            return this.hasStageSmartReply;
        }

        public final int getNotificationId() {
            return this.push.getMessageId().hashCode();
        }

        @NotNull
        public final NewMailPush getPush() {
            return this.push;
        }

        @NotNull
        public final List<SmartReply> getSmartReplies() {
            return this.smartReplies;
        }

        public final boolean hasSmartReplies() {
            return !this.smartReplies.isEmpty();
        }

        public int hashCode() {
            MailBoxFolder mailBoxFolder = this.folder;
            return ((((((((mailBoxFolder == null ? 0 : mailBoxFolder.hashCode()) * 31) + this.push.hashCode()) * 31) + this.smartReplies.hashCode()) * 31) + Boolean.hashCode(this.hasStageSmartReply)) * 31) + this.category.hashCode();
        }

        public final boolean isDefaultSmartReplies() {
            List<SmartReply> list = this.smartReplies;
            if ((list instanceof Collection) && list.isEmpty()) {
                return false;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((SmartReply) it.next()).isDefault()) {
                    return true;
                }
            }
            return false;
        }

        @NotNull
        public String toString() {
            return "NotificationData(folder=" + this.folder + ", push=" + this.push + ", smartReplies=" + this.smartReplies + ", hasStageSmartReply=" + this.hasStageSmartReply + ", category=" + this.category + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/util/push/Entity$ThreadIdAndSubject;", "", "threadId", "", "subj", "", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;)V", "getThreadId", "()Ljava/lang/String;", "getSubj", "()Ljava/lang/CharSequence;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ThreadIdAndSubject {
        public static final int $stable = 8;

        @NotNull
        private final CharSequence subj;

        @Nullable
        private final String threadId;

        public ThreadIdAndSubject(@Nullable String str, @NotNull CharSequence subj) {
            Intrinsics.checkNotNullParameter(subj, "subj");
            this.threadId = str;
            this.subj = subj;
        }

        public static /* synthetic */ ThreadIdAndSubject copy$default(ThreadIdAndSubject threadIdAndSubject, String str, CharSequence charSequence, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = threadIdAndSubject.threadId;
            }
            if ((i10 & 2) != 0) {
                charSequence = threadIdAndSubject.subj;
            }
            return threadIdAndSubject.copy(str, charSequence);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getThreadId() {
            return this.threadId;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final CharSequence getSubj() {
            return this.subj;
        }

        @NotNull
        public final ThreadIdAndSubject copy(@Nullable String threadId, @NotNull CharSequence subj) {
            Intrinsics.checkNotNullParameter(subj, "subj");
            return new ThreadIdAndSubject(threadId, subj);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ThreadIdAndSubject)) {
                return false;
            }
            ThreadIdAndSubject threadIdAndSubject = (ThreadIdAndSubject) other;
            return Intrinsics.areEqual(this.threadId, threadIdAndSubject.threadId) && Intrinsics.areEqual(this.subj, threadIdAndSubject.subj);
        }

        @NotNull
        public final CharSequence getSubj() {
            return this.subj;
        }

        @Nullable
        public final String getThreadId() {
            return this.threadId;
        }

        public int hashCode() {
            String str = this.threadId;
            return ((str == null ? 0 : str.hashCode()) * 31) + this.subj.hashCode();
        }

        @NotNull
        public String toString() {
            return "ThreadIdAndSubject(threadId=" + this.threadId + ", subj=" + ((Object) this.subj) + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00062\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003:\u0001\u0006B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/util/push/Entity$ThreadIdAndSubjectList;", "Ljava/util/ArrayList;", "Lru/mail/util/push/Entity$ThreadIdAndSubject;", "Lkotlin/collections/ArrayList;", "<init>", "()V", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ThreadIdAndSubjectList extends ArrayList<ThreadIdAndSubject> {
        private static final long serialVersionUID = 1683985815366109484L;
        public static final int $stable = 8;

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof ThreadIdAndSubject) {
                return contains((ThreadIdAndSubject) obj);
            }
            return false;
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof ThreadIdAndSubject) {
                return indexOf((ThreadIdAndSubject) obj);
            }
            return -1;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof ThreadIdAndSubject) {
                return lastIndexOf((ThreadIdAndSubject) obj);
            }
            return -1;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ ThreadIdAndSubject remove(int i10) {
            return removeAt(i10);
        }

        public /* bridge */ ThreadIdAndSubject removeAt(int i10) {
            return (ThreadIdAndSubject) super.remove(i10);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ int size() {
            return getSize();
        }

        public /* bridge */ boolean contains(ThreadIdAndSubject threadIdAndSubject) {
            return super.contains((Object) threadIdAndSubject);
        }

        public /* bridge */ int indexOf(ThreadIdAndSubject threadIdAndSubject) {
            return super.indexOf((Object) threadIdAndSubject);
        }

        public /* bridge */ int lastIndexOf(ThreadIdAndSubject threadIdAndSubject) {
            return super.lastIndexOf((Object) threadIdAndSubject);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof ThreadIdAndSubject) {
                return remove((ThreadIdAndSubject) obj);
            }
            return false;
        }

        public /* bridge */ boolean remove(ThreadIdAndSubject threadIdAndSubject) {
            return super.remove((Object) threadIdAndSubject);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00072\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0004:\u0001\u0007B\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lru/mail/util/push/Entity$ThreadIdCountSender;", "Ljava/util/HashMap;", "", "Lru/mail/util/push/Entity$CountAndSender;", "Lkotlin/collections/HashMap;", "<init>", "()V", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ThreadIdCountSender extends HashMap<String, CountAndSender> {
        private static final long serialVersionUID = 3336128152158590374L;
        public static final int $stable = 8;

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof String) {
                return containsKey((String) obj);
            }
            return false;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsValue(Object obj) {
            if (obj instanceof CountAndSender) {
                return containsValue((CountAndSender) obj);
            }
            return false;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<Map.Entry<String, CountAndSender>> entrySet() {
            return getEntries();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            if (obj instanceof String) {
                return get((String) obj);
            }
            return null;
        }

        public /* bridge */ Set<Map.Entry<String, CountAndSender>> getEntries() {
            return super.entrySet();
        }

        public /* bridge */ Set<String> getKeys() {
            return super.keySet();
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
            return !(obj instanceof String) ? obj2 : getOrDefault((String) obj, (CountAndSender) obj2);
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ Collection<CountAndSender> getValues() {
            return super.values();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<String> keySet() {
            return getKeys();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object remove(Object obj) {
            if (obj instanceof String) {
                return remove((String) obj);
            }
            return null;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Collection<CountAndSender> values() {
            return getValues();
        }

        public /* bridge */ boolean containsKey(String str) {
            return super.containsKey((Object) str);
        }

        public /* bridge */ boolean containsValue(CountAndSender countAndSender) {
            return super.containsValue((Object) countAndSender);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ CountAndSender get(Object obj) {
            if (obj instanceof String) {
                return get((String) obj);
            }
            return null;
        }

        public final /* bridge */ CountAndSender getOrDefault(Object obj, CountAndSender countAndSender) {
            return !(obj instanceof String) ? countAndSender : getOrDefault((String) obj, countAndSender);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ CountAndSender remove(Object obj) {
            if (obj instanceof String) {
                return remove((String) obj);
            }
            return null;
        }

        public /* bridge */ CountAndSender get(String str) {
            return (CountAndSender) super.get((Object) str);
        }

        public /* bridge */ CountAndSender getOrDefault(String str, CountAndSender countAndSender) {
            return (CountAndSender) super.getOrDefault((Object) str, countAndSender);
        }

        public /* bridge */ CountAndSender remove(String str) {
            return (CountAndSender) super.remove((Object) str);
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if ((obj instanceof String) && (obj2 instanceof CountAndSender)) {
                return remove((String) obj, (CountAndSender) obj2);
            }
            return false;
        }

        public /* bridge */ boolean remove(String str, CountAndSender countAndSender) {
            return super.remove((Object) str, (Object) countAndSender);
        }
    }
}
