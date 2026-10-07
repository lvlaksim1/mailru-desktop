package ru.mail.util.log;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J}\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012¨\u0006("}, d2 = {"Lru/mail/util/log/LogConstraints;", "", "captchaRequestConstraint", "Lru/mail/util/log/FilteringStrategy$Constraint;", "httpAuthorizeCommandConstraint", "authorizeRequestCommandConstraint", "", "mpopTokenRequestConstraint", "oAuth2HelperConstraint", "legacyMpopSessionConstraint", "tokenParserConstraint", "cachingAccountManagerConstraint", "<init>", "(Lru/mail/util/log/FilteringStrategy$Constraint;Lru/mail/util/log/FilteringStrategy$Constraint;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getCaptchaRequestConstraint", "()Lru/mail/util/log/FilteringStrategy$Constraint;", "getHttpAuthorizeCommandConstraint", "getAuthorizeRequestCommandConstraint", "()Ljava/util/List;", "getMpopTokenRequestConstraint", "getOAuth2HelperConstraint", "getLegacyMpopSessionConstraint", "getTokenParserConstraint", "getCachingAccountManagerConstraint", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "", "log_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LogConstraints {

    @NotNull
    private final List<FilteringStrategy.Constraint> authorizeRequestCommandConstraint;

    @NotNull
    private final List<FilteringStrategy.Constraint> cachingAccountManagerConstraint;

    @NotNull
    private final FilteringStrategy.Constraint captchaRequestConstraint;

    @NotNull
    private final FilteringStrategy.Constraint httpAuthorizeCommandConstraint;

    @NotNull
    private final List<FilteringStrategy.Constraint> legacyMpopSessionConstraint;

    @NotNull
    private final List<FilteringStrategy.Constraint> mpopTokenRequestConstraint;

    @NotNull
    private final List<FilteringStrategy.Constraint> oAuth2HelperConstraint;

    @NotNull
    private final List<FilteringStrategy.Constraint> tokenParserConstraint;

    /* JADX WARN: Multi-variable type inference failed */
    public LogConstraints(@NotNull FilteringStrategy.Constraint captchaRequestConstraint, @NotNull FilteringStrategy.Constraint httpAuthorizeCommandConstraint, @NotNull List<? extends FilteringStrategy.Constraint> authorizeRequestCommandConstraint, @NotNull List<? extends FilteringStrategy.Constraint> mpopTokenRequestConstraint, @NotNull List<? extends FilteringStrategy.Constraint> oAuth2HelperConstraint, @NotNull List<? extends FilteringStrategy.Constraint> legacyMpopSessionConstraint, @NotNull List<? extends FilteringStrategy.Constraint> tokenParserConstraint, @NotNull List<? extends FilteringStrategy.Constraint> cachingAccountManagerConstraint) {
        Intrinsics.checkNotNullParameter(captchaRequestConstraint, "captchaRequestConstraint");
        Intrinsics.checkNotNullParameter(httpAuthorizeCommandConstraint, "httpAuthorizeCommandConstraint");
        Intrinsics.checkNotNullParameter(authorizeRequestCommandConstraint, "authorizeRequestCommandConstraint");
        Intrinsics.checkNotNullParameter(mpopTokenRequestConstraint, "mpopTokenRequestConstraint");
        Intrinsics.checkNotNullParameter(oAuth2HelperConstraint, "oAuth2HelperConstraint");
        Intrinsics.checkNotNullParameter(legacyMpopSessionConstraint, "legacyMpopSessionConstraint");
        Intrinsics.checkNotNullParameter(tokenParserConstraint, "tokenParserConstraint");
        Intrinsics.checkNotNullParameter(cachingAccountManagerConstraint, "cachingAccountManagerConstraint");
        this.captchaRequestConstraint = captchaRequestConstraint;
        this.httpAuthorizeCommandConstraint = httpAuthorizeCommandConstraint;
        this.authorizeRequestCommandConstraint = authorizeRequestCommandConstraint;
        this.mpopTokenRequestConstraint = mpopTokenRequestConstraint;
        this.oAuth2HelperConstraint = oAuth2HelperConstraint;
        this.legacyMpopSessionConstraint = legacyMpopSessionConstraint;
        this.tokenParserConstraint = tokenParserConstraint;
        this.cachingAccountManagerConstraint = cachingAccountManagerConstraint;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LogConstraints copy$default(LogConstraints logConstraints, FilteringStrategy.Constraint constraint, FilteringStrategy.Constraint constraint2, List list, List list2, List list3, List list4, List list5, List list6, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            constraint = logConstraints.captchaRequestConstraint;
        }
        if ((i10 & 2) != 0) {
            constraint2 = logConstraints.httpAuthorizeCommandConstraint;
        }
        if ((i10 & 4) != 0) {
            list = logConstraints.authorizeRequestCommandConstraint;
        }
        if ((i10 & 8) != 0) {
            list2 = logConstraints.mpopTokenRequestConstraint;
        }
        if ((i10 & 16) != 0) {
            list3 = logConstraints.oAuth2HelperConstraint;
        }
        if ((i10 & 32) != 0) {
            list4 = logConstraints.legacyMpopSessionConstraint;
        }
        if ((i10 & 64) != 0) {
            list5 = logConstraints.tokenParserConstraint;
        }
        if ((i10 & 128) != 0) {
            list6 = logConstraints.cachingAccountManagerConstraint;
        }
        List list7 = list5;
        List list8 = list6;
        List list9 = list3;
        List list10 = list4;
        return logConstraints.copy(constraint, constraint2, list, list2, list9, list10, list7, list8);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FilteringStrategy.Constraint getCaptchaRequestConstraint() {
        return this.captchaRequestConstraint;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FilteringStrategy.Constraint getHttpAuthorizeCommandConstraint() {
        return this.httpAuthorizeCommandConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> component3() {
        return this.authorizeRequestCommandConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> component4() {
        return this.mpopTokenRequestConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> component5() {
        return this.oAuth2HelperConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> component6() {
        return this.legacyMpopSessionConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> component7() {
        return this.tokenParserConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> component8() {
        return this.cachingAccountManagerConstraint;
    }

    @NotNull
    public final LogConstraints copy(@NotNull FilteringStrategy.Constraint captchaRequestConstraint, @NotNull FilteringStrategy.Constraint httpAuthorizeCommandConstraint, @NotNull List<? extends FilteringStrategy.Constraint> authorizeRequestCommandConstraint, @NotNull List<? extends FilteringStrategy.Constraint> mpopTokenRequestConstraint, @NotNull List<? extends FilteringStrategy.Constraint> oAuth2HelperConstraint, @NotNull List<? extends FilteringStrategy.Constraint> legacyMpopSessionConstraint, @NotNull List<? extends FilteringStrategy.Constraint> tokenParserConstraint, @NotNull List<? extends FilteringStrategy.Constraint> cachingAccountManagerConstraint) {
        Intrinsics.checkNotNullParameter(captchaRequestConstraint, "captchaRequestConstraint");
        Intrinsics.checkNotNullParameter(httpAuthorizeCommandConstraint, "httpAuthorizeCommandConstraint");
        Intrinsics.checkNotNullParameter(authorizeRequestCommandConstraint, "authorizeRequestCommandConstraint");
        Intrinsics.checkNotNullParameter(mpopTokenRequestConstraint, "mpopTokenRequestConstraint");
        Intrinsics.checkNotNullParameter(oAuth2HelperConstraint, "oAuth2HelperConstraint");
        Intrinsics.checkNotNullParameter(legacyMpopSessionConstraint, "legacyMpopSessionConstraint");
        Intrinsics.checkNotNullParameter(tokenParserConstraint, "tokenParserConstraint");
        Intrinsics.checkNotNullParameter(cachingAccountManagerConstraint, "cachingAccountManagerConstraint");
        return new LogConstraints(captchaRequestConstraint, httpAuthorizeCommandConstraint, authorizeRequestCommandConstraint, mpopTokenRequestConstraint, oAuth2HelperConstraint, legacyMpopSessionConstraint, tokenParserConstraint, cachingAccountManagerConstraint);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogConstraints)) {
            return false;
        }
        LogConstraints logConstraints = (LogConstraints) other;
        return Intrinsics.areEqual(this.captchaRequestConstraint, logConstraints.captchaRequestConstraint) && Intrinsics.areEqual(this.httpAuthorizeCommandConstraint, logConstraints.httpAuthorizeCommandConstraint) && Intrinsics.areEqual(this.authorizeRequestCommandConstraint, logConstraints.authorizeRequestCommandConstraint) && Intrinsics.areEqual(this.mpopTokenRequestConstraint, logConstraints.mpopTokenRequestConstraint) && Intrinsics.areEqual(this.oAuth2HelperConstraint, logConstraints.oAuth2HelperConstraint) && Intrinsics.areEqual(this.legacyMpopSessionConstraint, logConstraints.legacyMpopSessionConstraint) && Intrinsics.areEqual(this.tokenParserConstraint, logConstraints.tokenParserConstraint) && Intrinsics.areEqual(this.cachingAccountManagerConstraint, logConstraints.cachingAccountManagerConstraint);
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> getAuthorizeRequestCommandConstraint() {
        return this.authorizeRequestCommandConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> getCachingAccountManagerConstraint() {
        return this.cachingAccountManagerConstraint;
    }

    @NotNull
    public final FilteringStrategy.Constraint getCaptchaRequestConstraint() {
        return this.captchaRequestConstraint;
    }

    @NotNull
    public final FilteringStrategy.Constraint getHttpAuthorizeCommandConstraint() {
        return this.httpAuthorizeCommandConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> getLegacyMpopSessionConstraint() {
        return this.legacyMpopSessionConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> getMpopTokenRequestConstraint() {
        return this.mpopTokenRequestConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> getOAuth2HelperConstraint() {
        return this.oAuth2HelperConstraint;
    }

    @NotNull
    public final List<FilteringStrategy.Constraint> getTokenParserConstraint() {
        return this.tokenParserConstraint;
    }

    public int hashCode() {
        return (((((((((((((this.captchaRequestConstraint.hashCode() * 31) + this.httpAuthorizeCommandConstraint.hashCode()) * 31) + this.authorizeRequestCommandConstraint.hashCode()) * 31) + this.mpopTokenRequestConstraint.hashCode()) * 31) + this.oAuth2HelperConstraint.hashCode()) * 31) + this.legacyMpopSessionConstraint.hashCode()) * 31) + this.tokenParserConstraint.hashCode()) * 31) + this.cachingAccountManagerConstraint.hashCode();
    }

    @NotNull
    public String toString() {
        return "LogConstraints(captchaRequestConstraint=" + this.captchaRequestConstraint + ", httpAuthorizeCommandConstraint=" + this.httpAuthorizeCommandConstraint + ", authorizeRequestCommandConstraint=" + this.authorizeRequestCommandConstraint + ", mpopTokenRequestConstraint=" + this.mpopTokenRequestConstraint + ", oAuth2HelperConstraint=" + this.oAuth2HelperConstraint + ", legacyMpopSessionConstraint=" + this.legacyMpopSessionConstraint + ", tokenParserConstraint=" + this.tokenParserConstraint + ", cachingAccountManagerConstraint=" + this.cachingAccountManagerConstraint + ")";
    }
}
