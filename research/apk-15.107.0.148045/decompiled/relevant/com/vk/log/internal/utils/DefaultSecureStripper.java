package com.vk.log.internal.utils;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.vk.superapp.sessionmanagment.impl.data.source.SessionSQLiteHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0002\u0012\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ)\u0010\b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0004\b\b\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/vk/log/internal/utils/DefaultSecureStripper;", "", "<init>", "()V", "Lkotlin/text/Regex;", "regex", "", "replacement", "withRule", "(Lkotlin/text/Regex;Ljava/lang/String;)Lcom/vk/log/internal/utils/DefaultSecureStripper;", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "", "(Lkotlin/text/Regex;Lkotlin/jvm/functions/Function1;)Lcom/vk/log/internal/utils/DefaultSecureStripper;", "msg", "strip", "(Ljava/lang/String;)Ljava/lang/String;", "Companion", "StripRule", "log_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDefaultSecureStripper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultSecureStripper.kt\ncom/vk/log/internal/utils/DefaultSecureStripper\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,84:1\n1803#2,3:85\n*S KotlinDebug\n*F\n+ 1 DefaultSecureStripper.kt\ncom/vk/log/internal/utils/DefaultSecureStripper\n*L\n47#1:85,3\n*E\n"})
public final class DefaultSecureStripper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE;

    @NotNull
    private static final List<String> golbilkvmocb;

    @NotNull
    private static final String golbilkvmocc;

    @NotNull
    private static final DefaultSecureStripper golbilkvmocd;

    @NotNull
    private final ArrayList golbilkvmoca = new ArrayList();

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\r\u001a\u00020\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/vk/log/internal/utils/DefaultSecureStripper$Companion;", "", "<init>", "()V", "DEFAULT_KEYS", "", "", "SENSITIVE_VALUE_PATTERN", "BEARER_PATTERN", "SIGN_STRIPPER_DEFAULT", "Lcom/vk/log/internal/utils/DefaultSecureStripper;", "getSIGN_STRIPPER_DEFAULT", "()Lcom/vk/log/internal/utils/DefaultSecureStripper;", "generateBaseStripper", UserMetadata.KEYDATA_FILENAME, "", "log_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DefaultSecureStripper generateBaseStripper$default(Companion companion, Collection collection, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                collection = DefaultSecureStripper.golbilkvmocb;
            }
            return companion.generateBaseStripper(collection);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence golbilkvmoca(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return match.getGroupValues().get(1) + "=<HIDE>";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence golbilkvmocb(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return match.getGroupValues().get(1) + ":<HIDE>";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence golbilkvmocc(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return "\"" + match.getGroupValues().get(1) + "\":\"<HIDE>\"";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence golbilkvmocd(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return "\"" + match.getGroupValues().get(1) + ":<HIDE>\"}";
        }

        @NotNull
        public final DefaultSecureStripper generateBaseStripper(@NotNull Collection<String> keys) {
            Intrinsics.checkNotNullParameter(keys, "keys");
            DefaultSecureStripper defaultSecureStripper = new DefaultSecureStripper();
            String str = "(" + CollectionsKt.joinToString$default(keys, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + ")=[a-zA-Z0-9._%-]+";
            RegexOption regexOption = RegexOption.IGNORE_CASE;
            return defaultSecureStripper.withRule(new Regex(str, regexOption), new Function1() { // from class: com.vk.log.internal.utils.a
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DefaultSecureStripper.Companion.golbilkvmoca((MatchResult) obj);
                }
            }).withRule(new Regex("(" + CollectionsKt.joinToString$default(keys, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + "):[a-zA-Z0-9._%-]+", regexOption), new Function1() { // from class: com.vk.log.internal.utils.b
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DefaultSecureStripper.Companion.golbilkvmocb((MatchResult) obj);
                }
            }).withRule(new Regex("\"(" + CollectionsKt.joinToString$default(keys, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + ")\":\"[a-zA-Z0-9._%-]+\"", regexOption), new Function1() { // from class: com.vk.log.internal.utils.c
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DefaultSecureStripper.Companion.golbilkvmocc((MatchResult) obj);
                }
            }).withRule(new Regex("\\{\"key\":\"(" + CollectionsKt.joinToString$default(keys, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + ")\",\"value\":\"[a-zA-Z0-9._%-]+\"", regexOption), new Function1() { // from class: com.vk.log.internal.utils.d
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DefaultSecureStripper.Companion.golbilkvmocd((MatchResult) obj);
                }
            }).withRule(new Regex(DefaultSecureStripper.golbilkvmocc), "Bearer <HIDE> ");
        }

        @NotNull
        public final DefaultSecureStripper getSIGN_STRIPPER_DEFAULT() {
            return DefaultSecureStripper.golbilkvmocd;
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R&\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule;", "", "Lkotlin/text/Regex;", "golbilkvmoca", "Lkotlin/text/Regex;", "getRegex", "()Lkotlin/text/Regex;", "regex", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "", "golbilkvmocb", "Lkotlin/jvm/functions/Function1;", "getReplacement", "()Lkotlin/jvm/functions/Function1;", "replacement", "StringReplacement", "MatchGroupReplacement", "Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule$MatchGroupReplacement;", "Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule$StringReplacement;", "log_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static abstract class StripRule {

        /* JADX INFO: renamed from: golbilkvmoca, reason: from kotlin metadata */
        @NotNull
        private final Regex regex;

        /* JADX INFO: renamed from: golbilkvmocb, reason: from kotlin metadata */
        @NotNull
        private final Function1<MatchResult, CharSequence> replacement;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\f\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR&\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule$MatchGroupReplacement;", "Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule;", "Lkotlin/text/Regex;", "regex", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "", "replacement", "<init>", "(Lkotlin/text/Regex;Lkotlin/jvm/functions/Function1;)V", "golbilkvmocc", "Lkotlin/text/Regex;", "getRegex", "()Lkotlin/text/Regex;", "golbilkvmocd", "Lkotlin/jvm/functions/Function1;", "getReplacement", "()Lkotlin/jvm/functions/Function1;", "log_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class MatchGroupReplacement extends StripRule {

            /* JADX INFO: renamed from: golbilkvmocc, reason: from kotlin metadata */
            @NotNull
            private final Regex regex;

            /* JADX INFO: renamed from: golbilkvmocd, reason: from kotlin metadata */
            @NotNull
            private final Function1<MatchResult, CharSequence> replacement;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public MatchGroupReplacement(@NotNull Regex regex, @NotNull Function1<? super MatchResult, ? extends CharSequence> replacement) {
                super(regex, replacement, null);
                Intrinsics.checkNotNullParameter(regex, "regex");
                Intrinsics.checkNotNullParameter(replacement, "replacement");
                this.regex = regex;
                this.replacement = replacement;
            }

            @Override // com.vk.log.internal.utils.DefaultSecureStripper.StripRule
            @NotNull
            public Regex getRegex() {
                return this.regex;
            }

            @Override // com.vk.log.internal.utils.DefaultSecureStripper.StripRule
            @NotNull
            public Function1<MatchResult, CharSequence> getReplacement() {
                return this.replacement;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule$StringReplacement;", "Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule;", "Lkotlin/text/Regex;", "regex", "", "replacementString", "<init>", "(Lkotlin/text/Regex;Ljava/lang/String;)V", "component1", "()Lkotlin/text/Regex;", "component2", "()Ljava/lang/String;", "copy", "(Lkotlin/text/Regex;Ljava/lang/String;)Lcom/vk/log/internal/utils/DefaultSecureStripper$StripRule$StringReplacement;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "golbilkvmocc", "Lkotlin/text/Regex;", "getRegex", "golbilkvmocd", "Ljava/lang/String;", "getReplacementString", "log_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class StringReplacement extends StripRule {

            /* JADX INFO: renamed from: golbilkvmocc, reason: from kotlin metadata */
            @NotNull
            private final Regex regex;

            /* JADX INFO: renamed from: golbilkvmocd, reason: from kotlin metadata */
            @NotNull
            private final String replacementString;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StringReplacement(@NotNull Regex regex, @NotNull final String replacementString) {
                super(regex, new Function1() { // from class: com.vk.log.internal.utils.e
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DefaultSecureStripper.StripRule.StringReplacement.golbilkvmoca(replacementString, (MatchResult) obj);
                    }
                }, null);
                Intrinsics.checkNotNullParameter(regex, "regex");
                Intrinsics.checkNotNullParameter(replacementString, "replacementString");
                this.regex = regex;
                this.replacementString = replacementString;
            }

            public static /* synthetic */ StringReplacement copy$default(StringReplacement stringReplacement, Regex regex, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    regex = stringReplacement.regex;
                }
                if ((i10 & 2) != 0) {
                    str = stringReplacement.replacementString;
                }
                return stringReplacement.copy(regex, str);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final CharSequence golbilkvmoca(String str, MatchResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return str;
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Regex getRegex() {
                return this.regex;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getReplacementString() {
                return this.replacementString;
            }

            @NotNull
            public final StringReplacement copy(@NotNull Regex regex, @NotNull String replacementString) {
                Intrinsics.checkNotNullParameter(regex, "regex");
                Intrinsics.checkNotNullParameter(replacementString, "replacementString");
                return new StringReplacement(regex, replacementString);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof StringReplacement)) {
                    return false;
                }
                StringReplacement stringReplacement = (StringReplacement) other;
                return Intrinsics.areEqual(this.regex, stringReplacement.regex) && Intrinsics.areEqual(this.replacementString, stringReplacement.replacementString);
            }

            @Override // com.vk.log.internal.utils.DefaultSecureStripper.StripRule
            @NotNull
            public Regex getRegex() {
                return this.regex;
            }

            @NotNull
            public final String getReplacementString() {
                return this.replacementString;
            }

            public int hashCode() {
                return this.replacementString.hashCode() + (this.regex.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return "StringReplacement(regex=" + this.regex + ", replacementString=" + this.replacementString + ')';
            }
        }

        private StripRule() {
            throw null;
        }

        @NotNull
        public Regex getRegex() {
            return this.regex;
        }

        @NotNull
        public Function1<MatchResult, CharSequence> getReplacement() {
            return this.replacement;
        }

        public StripRule(Regex regex, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
            this.regex = regex;
            this.replacement = function1;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{"sign", "key", "access_token", "access_tokens", "wat", "wrt", SessionSQLiteHelper.COLUMN_WEBVIEW_AT, SessionSQLiteHelper.COLUMN_WEBVIEW_RT, "exchange_token", "exchange_tokens", "common_token", "message", "httoken"});
        golbilkvmocb = listListOf;
        golbilkvmocc = "Bearer [a-zA-Z0-9._%-]+";
        golbilkvmocd = companion.generateBaseStripper(listListOf);
    }

    @NotNull
    public final String strip(@Nullable String msg) {
        ArrayList arrayList = this.golbilkvmoca;
        int size = arrayList.size();
        int i10 = 0;
        while (i10 < size) {
            Object obj = arrayList.get(i10);
            i10++;
            StripRule stripRule = (StripRule) obj;
            msg = msg != null ? stripRule.getRegex().replace(msg, stripRule.getReplacement()) : null;
        }
        return msg == null ? "" : msg;
    }

    @NotNull
    public final DefaultSecureStripper withRule(@NotNull Regex regex, @NotNull String replacement) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        this.golbilkvmoca.add(new StripRule.StringReplacement(regex, Regex.INSTANCE.escapeReplacement(replacement)));
        return this;
    }

    @NotNull
    public final DefaultSecureStripper withRule(@NotNull Regex regex, @NotNull Function1<? super MatchResult, ? extends CharSequence> replacement) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        this.golbilkvmoca.add(new StripRule.MatchGroupReplacement(regex, replacement));
        return this;
    }
}
