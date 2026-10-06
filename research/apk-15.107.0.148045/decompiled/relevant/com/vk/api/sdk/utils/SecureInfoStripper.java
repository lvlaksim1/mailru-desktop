package com.vk.api.sdk.utils;

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
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\"\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fJ\u0010\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/vk/api/sdk/utils/SecureInfoStripper;", "", "<init>", "()V", "stripRules", "", "Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule;", "withRule", "regex", "Lkotlin/text/Regex;", "replacement", "", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "", "strip", "msg", "StripRule", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSecureInfoStripper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SecureInfoStripper.kt\ncom/vk/api/sdk/utils/SecureInfoStripper\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,83:1\n1803#2,3:84\n*S KotlinDebug\n*F\n+ 1 SecureInfoStripper.kt\ncom/vk/api/sdk/utils/SecureInfoStripper\n*L\n47#1:84,3\n*E\n"})
public final class SecureInfoStripper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE;

    @NotNull
    private static final List<String> DEFAULT_KEYS;

    @NotNull
    public static final String SENSITIVE_VALUE_PATTERN = "[a-zA-Z0-9._%-]+";

    @NotNull
    private static final SecureInfoStripper SIGN_STRIPPER_DEFAULT;

    @NotNull
    private final List<StripRule> stripRules = new ArrayList();

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\u00020\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/vk/api/sdk/utils/SecureInfoStripper$Companion;", "", "<init>", "()V", "DEFAULT_KEYS", "", "", "SENSITIVE_VALUE_PATTERN", "SIGN_STRIPPER_DEFAULT", "Lcom/vk/api/sdk/utils/SecureInfoStripper;", "getSIGN_STRIPPER_DEFAULT", "()Lcom/vk/api/sdk/utils/SecureInfoStripper;", "generateBaseStripper", UserMetadata.KEYDATA_FILENAME, "", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SecureInfoStripper generateBaseStripper$default(Companion companion, Collection collection, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                collection = SecureInfoStripper.DEFAULT_KEYS;
            }
            return companion.generateBaseStripper(collection);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence generateBaseStripper$lambda$0(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return ((Object) match.getGroupValues().get(1)) + "=<HIDE>";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence generateBaseStripper$lambda$1(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return ((Object) match.getGroupValues().get(1)) + ":<HIDE>";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence generateBaseStripper$lambda$2(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return "\"" + ((Object) match.getGroupValues().get(1)) + "\":\"<HIDE>\"";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence generateBaseStripper$lambda$3(MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            return "\"" + ((Object) match.getGroupValues().get(1)) + ":<HIDE>\"}";
        }

        @NotNull
        public final SecureInfoStripper generateBaseStripper(@NotNull Collection<String> keys) {
            Intrinsics.checkNotNullParameter(keys, "keys");
            SecureInfoStripper secureInfoStripper = new SecureInfoStripper();
            Collection<String> collection = keys;
            String str = "(" + CollectionsKt.joinToString$default(collection, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + ")=[a-zA-Z0-9._%-]+";
            RegexOption regexOption = RegexOption.IGNORE_CASE;
            return secureInfoStripper.withRule(new Regex(str, regexOption), new Function1() { // from class: com.vk.api.sdk.utils.b
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SecureInfoStripper.Companion.generateBaseStripper$lambda$0((MatchResult) obj);
                }
            }).withRule(new Regex("(" + CollectionsKt.joinToString$default(collection, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + "):[a-zA-Z0-9._%-]+", regexOption), new Function1() { // from class: com.vk.api.sdk.utils.c
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SecureInfoStripper.Companion.generateBaseStripper$lambda$1((MatchResult) obj);
                }
            }).withRule(new Regex("\"(" + CollectionsKt.joinToString$default(collection, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + ")\":\"[a-zA-Z0-9._%-]+\"", regexOption), new Function1() { // from class: com.vk.api.sdk.utils.d
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SecureInfoStripper.Companion.generateBaseStripper$lambda$2((MatchResult) obj);
                }
            }).withRule(new Regex("\\{\"key\":\"(" + CollectionsKt.joinToString$default(collection, HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null) + ")\",\"value\":\"[a-zA-Z0-9._%-]+\"", regexOption), new Function1() { // from class: com.vk.api.sdk.utils.e
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SecureInfoStripper.Companion.generateBaseStripper$lambda$3((MatchResult) obj);
                }
            });
        }

        @NotNull
        public final SecureInfoStripper getSIGN_STRIPPER_DEFAULT() {
            return SecureInfoStripper.SIGN_STRIPPER_DEFAULT;
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB%\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0001\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule;", "", "regex", "Lkotlin/text/Regex;", "replacement", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "", "<init>", "(Lkotlin/text/Regex;Lkotlin/jvm/functions/Function1;)V", "getRegex", "()Lkotlin/text/Regex;", "getReplacement", "()Lkotlin/jvm/functions/Function1;", "StringReplacement", "MatchGroupReplacement", "Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule$MatchGroupReplacement;", "Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule$StringReplacement;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static abstract class StripRule {

        @NotNull
        private final Regex regex;

        @NotNull
        private final Function1<MatchResult, CharSequence> replacement;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule$MatchGroupReplacement;", "Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule;", "regex", "Lkotlin/text/Regex;", "replacement", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "", "<init>", "(Lkotlin/text/Regex;Lkotlin/jvm/functions/Function1;)V", "getRegex", "()Lkotlin/text/Regex;", "getReplacement", "()Lkotlin/jvm/functions/Function1;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class MatchGroupReplacement extends StripRule {

            @NotNull
            private final Regex regex;

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

            @Override // com.vk.api.sdk.utils.SecureInfoStripper.StripRule
            @NotNull
            public Regex getRegex() {
                return this.regex;
            }

            @Override // com.vk.api.sdk.utils.SecureInfoStripper.StripRule
            @NotNull
            public Function1<MatchResult, CharSequence> getReplacement() {
                return this.replacement;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes3.dex */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule$StringReplacement;", "Lcom/vk/api/sdk/utils/SecureInfoStripper$StripRule;", "regex", "Lkotlin/text/Regex;", "replacementString", "", "<init>", "(Lkotlin/text/Regex;Ljava/lang/String;)V", "getRegex", "()Lkotlin/text/Regex;", "getReplacementString", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class StringReplacement extends StripRule {

            @NotNull
            private final Regex regex;

            @NotNull
            private final String replacementString;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StringReplacement(@NotNull Regex regex, @NotNull final String replacementString) {
                super(regex, new Function1() { // from class: com.vk.api.sdk.utils.f
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SecureInfoStripper.StripRule.StringReplacement._init_$lambda$0(replacementString, (MatchResult) obj);
                    }
                }, null);
                Intrinsics.checkNotNullParameter(regex, "regex");
                Intrinsics.checkNotNullParameter(replacementString, "replacementString");
                this.regex = regex;
                this.replacementString = replacementString;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final CharSequence _init_$lambda$0(String str, MatchResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return str;
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

            @Override // com.vk.api.sdk.utils.SecureInfoStripper.StripRule
            @NotNull
            public Regex getRegex() {
                return this.regex;
            }

            @NotNull
            public final String getReplacementString() {
                return this.replacementString;
            }

            public int hashCode() {
                return (this.regex.hashCode() * 31) + this.replacementString.hashCode();
            }

            @NotNull
            public String toString() {
                return "StringReplacement(regex=" + this.regex + ", replacementString=" + this.replacementString + ")";
            }
        }

        public /* synthetic */ StripRule(Regex regex, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
            this(regex, function1);
        }

        @NotNull
        public Regex getRegex() {
            return this.regex;
        }

        @NotNull
        public Function1<MatchResult, CharSequence> getReplacement() {
            return this.replacement;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private StripRule(Regex regex, Function1<? super MatchResult, ? extends CharSequence> function1) {
            this.regex = regex;
            this.replacement = function1;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{"sign", "key", "access_token", "access_tokens", "wat", "wrt", SessionSQLiteHelper.COLUMN_WEBVIEW_AT, SessionSQLiteHelper.COLUMN_WEBVIEW_RT, "exchange_token", "exchange_tokens", "common_token", "message", "httoken"});
        DEFAULT_KEYS = listListOf;
        SIGN_STRIPPER_DEFAULT = companion.generateBaseStripper(listListOf);
    }

    @NotNull
    public final String strip(@Nullable String msg) {
        for (StripRule stripRule : this.stripRules) {
            msg = msg != null ? stripRule.getRegex().replace(msg, stripRule.getReplacement()) : null;
        }
        return msg == null ? "" : msg;
    }

    @NotNull
    public final SecureInfoStripper withRule(@NotNull Regex regex, @NotNull String replacement) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        this.stripRules.add(new StripRule.StringReplacement(regex, Regex.INSTANCE.escapeReplacement(replacement)));
        return this;
    }

    @NotNull
    public final SecureInfoStripper withRule(@NotNull Regex regex, @NotNull Function1<? super MatchResult, ? extends CharSequence> replacement) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        this.stripRules.add(new StripRule.MatchGroupReplacement(regex, replacement));
        return this;
    }
}
