package com.vk.api.sdk.okhttp;

import com.vk.api.sdk.utils.SecureInfoStripper;
import com.vk.api.sdk.utils.ThreadLocalDelegate;
import com.vk.api.sdk.utils.ThreadLocalDelegateKt;
import com.vk.api.sdk.utils.log.Logger;
import com.vk.superapp.sessionmanagment.impl.data.source.SessionSQLiteHelper;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 52\u00020\u0001:\u00015B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\rB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\u000eB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\u000fJ\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0016J\u0018\u00101\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00102\u001a\u00020\u0001H\u0014J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001b\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0015\u001a\u0004\b\u001c\u0010\u0019R-\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020!0\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0015\u001a\u0004\b\"\u0010#R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020(8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b)\u0010*¨\u00066"}, d2 = {"Lcom/vk/api/sdk/okhttp/LoggingInterceptor;", "Lokhttp3/Interceptor;", "filterCredentials", "", "keysToFilter", "", "", "logger", "Lcom/vk/api/sdk/utils/log/Logger;", "loggingPrefixer", "Lcom/vk/api/sdk/okhttp/LoggingPrefixer;", "<init>", "(ZLjava/util/Collection;Lcom/vk/api/sdk/utils/log/Logger;Lcom/vk/api/sdk/okhttp/LoggingPrefixer;)V", "(ZLcom/vk/api/sdk/utils/log/Logger;)V", "(ZLjava/util/Collection;Lcom/vk/api/sdk/utils/log/Logger;)V", "(ZLcom/vk/api/sdk/utils/log/Logger;Lcom/vk/api/sdk/okhttp/LoggingPrefixer;)V", "secureInfoStripper", "Lcom/vk/api/sdk/utils/SecureInfoStripper;", "getSecureInfoStripper", "()Lcom/vk/api/sdk/utils/SecureInfoStripper;", "secureInfoStripper$delegate", "Lkotlin/Lazy;", "kvKeysExtractorPattern", "Lkotlin/text/Regex;", "getKvKeysExtractorPattern", "()Lkotlin/text/Regex;", "kvKeysExtractorPattern$delegate", "kvKeysRestorePattern", "getKvKeysRestorePattern", "kvKeysRestorePattern$delegate", "restoreKVKeysTransformer", "Lkotlin/Function2;", "Lkotlin/text/MatchResult;", "", "getRestoreKVKeysTransformer", "()Lkotlin/jvm/functions/Function2;", "restoreKVKeysTransformer$delegate", "prefix", "Ljava/lang/ThreadLocal;", "delegate", "Lokhttp3/logging/HttpLoggingInterceptor;", "getDelegate", "()Lokhttp3/logging/HttpLoggingInterceptor;", "delegate$delegate", "Lcom/vk/api/sdk/utils/ThreadLocalDelegate;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "proceedLoggingChain", "logInterceptor", "removeSensitiveKeys", "msg", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class LoggingInterceptor implements Interceptor {

    @NotNull
    private static final Regex BEARER_REGEX;

    @NotNull
    private static final Map<Integer, HttpLoggingInterceptor.Level> levelsMap;

    /* JADX INFO: renamed from: delegate$delegate, reason: from kotlin metadata */
    @NotNull
    private final ThreadLocalDelegate delegate;
    private final boolean filterCredentials;

    @NotNull
    private final Collection<String> keysToFilter;

    /* JADX INFO: renamed from: kvKeysExtractorPattern$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy kvKeysExtractorPattern;

    /* JADX INFO: renamed from: kvKeysRestorePattern$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy kvKeysRestorePattern;

    @NotNull
    private final Logger logger;

    @NotNull
    private final LoggingPrefixer loggingPrefixer;

    @NotNull
    private ThreadLocal<String> prefix;

    /* JADX INFO: renamed from: restoreKVKeysTransformer$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy restoreKVKeysTransformer;

    /* JADX INFO: renamed from: secureInfoStripper$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy secureInfoStripper;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(LoggingInterceptor.class, "delegate", "getDelegate()Lokhttp3/logging/HttpLoggingInterceptor;", 0))};

    @NotNull
    private static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/vk/api/sdk/okhttp/LoggingInterceptor$Companion;", "", "<init>", "()V", "levelsMap", "", "", "Lokhttp3/logging/HttpLoggingInterceptor$Level;", "BEARER_REGEX", "Lkotlin/text/Regex;", "getBEARER_REGEX", "()Lkotlin/text/Regex;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Regex getBEARER_REGEX() {
            return LoggingInterceptor.BEARER_REGEX;
        }

        private Companion() {
        }
    }

    static {
        Logger.LogLevel logLevel = Logger.LogLevel.NONE;
        Integer numValueOf = Integer.valueOf(logLevel.getLevel());
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.NONE;
        levelsMap = MapsKt.mapOf(TuplesKt.to(numValueOf, level), TuplesKt.to(Integer.valueOf(Logger.LogLevel.ERROR.getLevel()), level), TuplesKt.to(Integer.valueOf(Logger.LogLevel.WARNING.getLevel()), HttpLoggingInterceptor.Level.BASIC), TuplesKt.to(Integer.valueOf(Logger.LogLevel.DEBUG.getLevel()), HttpLoggingInterceptor.Level.HEADERS), TuplesKt.to(Integer.valueOf(Logger.LogLevel.VERBOSE.getLevel()), HttpLoggingInterceptor.Level.BODY), TuplesKt.to(Integer.valueOf(logLevel.getLevel()), level));
        BEARER_REGEX = new Regex("Bearer [a-zA-Z0-9._%-]+");
    }

    public LoggingInterceptor(boolean z10, @NotNull Collection<String> keysToFilter, @NotNull Logger logger, @NotNull LoggingPrefixer loggingPrefixer) {
        Intrinsics.checkNotNullParameter(keysToFilter, "keysToFilter");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(loggingPrefixer, "loggingPrefixer");
        this.filterCredentials = z10;
        this.keysToFilter = keysToFilter;
        this.logger = logger;
        this.loggingPrefixer = loggingPrefixer;
        this.secureInfoStripper = LazyKt.lazy(new Function0() { // from class: com.vk.api.sdk.okhttp.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoggingInterceptor.secureInfoStripper_delegate$lambda$0(this.f40043a);
            }
        });
        this.kvKeysExtractorPattern = LazyKt.lazy(new Function0() { // from class: com.vk.api.sdk.okhttp.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoggingInterceptor.kvKeysExtractorPattern_delegate$lambda$1();
            }
        });
        this.kvKeysRestorePattern = LazyKt.lazy(new Function0() { // from class: com.vk.api.sdk.okhttp.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoggingInterceptor.kvKeysRestorePattern_delegate$lambda$2();
            }
        });
        this.restoreKVKeysTransformer = LazyKt.lazy(new Function0() { // from class: com.vk.api.sdk.okhttp.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoggingInterceptor.restoreKVKeysTransformer_delegate$lambda$4();
            }
        });
        this.prefix = new ThreadLocal<>();
        this.delegate = ThreadLocalDelegateKt.threadLocal(new Function0() { // from class: com.vk.api.sdk.okhttp.e
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoggingInterceptor.delegate_delegate$lambda$5(this.f40044a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HttpLoggingInterceptor delegate_delegate$lambda$5(final LoggingInterceptor loggingInterceptor) {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() { // from class: com.vk.api.sdk.okhttp.LoggingInterceptor$delegate$2$1
            private final String filterCredentials(String msg) {
                return this.this$0.removeSensitiveKeys(msg);
            }

            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public void log(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                String str = (String) this.this$0.prefix.get();
                if (str != null) {
                    String str2 = str + StringUtils.SPACE + message;
                    if (str2 != null) {
                        message = str2;
                    }
                }
                SecureInfoStripper secureInfoStripperWithRule = SecureInfoStripper.Companion.generateBaseStripper$default(SecureInfoStripper.INSTANCE, null, 1, null).withRule(LoggingInterceptor.Companion.getBEARER_REGEX(), "Bearer <HIDE> ");
                if (this.this$0.filterCredentials) {
                    message = filterCredentials(message);
                }
                Logger.DefaultImpls.log$default(this.this$0.logger, this.this$0.logger.getLogLevel().getValue(), secureInfoStripperWithRule.strip(message), null, 4, null);
            }
        });
    }

    private final HttpLoggingInterceptor getDelegate() {
        return (HttpLoggingInterceptor) this.delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final Regex getKvKeysExtractorPattern() {
        return (Regex) this.kvKeysExtractorPattern.getValue();
    }

    private final Regex getKvKeysRestorePattern() {
        return (Regex) this.kvKeysRestorePattern.getValue();
    }

    private final Function2<MatchResult, String, CharSequence> getRestoreKVKeysTransformer() {
        return (Function2) this.restoreKVKeysTransformer.getValue();
    }

    private final SecureInfoStripper getSecureInfoStripper() {
        return (SecureInfoStripper) this.secureInfoStripper.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Regex kvKeysExtractorPattern_delegate$lambda$1() {
        return new Regex("\\{\"key\":\"([a-zA-Z0-9._%-]+)\",\"value\":\"[^\"]*\"", RegexOption.IGNORE_CASE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Regex kvKeysRestorePattern_delegate$lambda$2() {
        return new Regex("(\\{\"key\":)<HIDE>(,\"value\":\"[^\"]*\")", RegexOption.IGNORE_CASE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String removeSensitiveKeys(String msg) {
        final Iterator it = SequencesKt.map(Regex.findAll$default(getKvKeysExtractorPattern(), msg, 0, 2, null), new Function1() { // from class: com.vk.api.sdk.okhttp.g
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LoggingInterceptor.removeSensitiveKeys$lambda$6((MatchResult) obj);
            }
        }).iterator();
        return getKvKeysRestorePattern().replace(getSecureInfoStripper().strip(msg), new Function1() { // from class: com.vk.api.sdk.okhttp.h
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LoggingInterceptor.removeSensitiveKeys$lambda$7(this.f40045a, it, (MatchResult) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String removeSensitiveKeys$lambda$6(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String lowerCase = it.getGroupValues().get(1).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final CharSequence removeSensitiveKeys$lambda$7(LoggingInterceptor loggingInterceptor, Iterator it, MatchResult matchResult) {
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        return (CharSequence) loggingInterceptor.getRestoreKVKeysTransformer().invoke(matchResult, it.next());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2 restoreKVKeysTransformer_delegate$lambda$4() {
        return new Function2() { // from class: com.vk.api.sdk.okhttp.f
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return LoggingInterceptor.restoreKVKeysTransformer_delegate$lambda$4$lambda$3((MatchResult) obj, (String) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String restoreKVKeysTransformer_delegate$lambda$4$lambda$3(MatchResult match, String key) {
        Intrinsics.checkNotNullParameter(match, "match");
        Intrinsics.checkNotNullParameter(key, "key");
        return ((Object) match.getGroupValues().get(1)) + "\"" + key + "\"" + ((Object) match.getGroupValues().get(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SecureInfoStripper secureInfoStripper_delegate$lambda$0(LoggingInterceptor loggingInterceptor) {
        return SecureInfoStripper.INSTANCE.generateBaseStripper(loggingInterceptor.keysToFilter);
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Logger.LogLevel value;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        RequestBody requestBodyBody = request.body();
        long jContentLength = requestBodyBody != null ? requestBodyBody.contentLength() : 0L;
        LogLevelRequestTag logLevelRequestTag = (LogLevelRequestTag) request.tag(LogLevelRequestTag.class);
        if (logLevelRequestTag == null || (value = logLevelRequestTag.getLevel()) == null) {
            value = this.logger.getLogLevel().getValue();
        }
        HttpLoggingInterceptor delegate = getDelegate();
        HttpLoggingInterceptor.Level level = (jContentLength > 4096 || jContentLength <= 0) ? levelsMap.get(Integer.valueOf(Math.min(Logger.LogLevel.WARNING.getLevel(), value.getLevel()))) : levelsMap.get(Integer.valueOf(value.getLevel()));
        Intrinsics.checkNotNull(level);
        delegate.level(level);
        this.prefix.set(this.loggingPrefixer.getPrefix());
        return proceedLoggingChain(chain, getDelegate());
    }

    @NotNull
    protected Response proceedLoggingChain(@NotNull Interceptor.Chain chain, @NotNull Interceptor logInterceptor) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(logInterceptor, "logInterceptor");
        return logInterceptor.intercept(chain);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoggingInterceptor(boolean z10, @NotNull Logger logger) {
        this(z10, CollectionsKt.listOf((Object[]) new String[]{"access_token", "key", SharedKt.PARAM_CLIENT_SECRET, SessionSQLiteHelper.COLUMN_WEBVIEW_AT, SessionSQLiteHelper.COLUMN_WEBVIEW_RT, "exchange_token", "exchange_tokens", "common_token"}), logger, new DefaultLoggingPrefixer());
        Intrinsics.checkNotNullParameter(logger, "logger");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoggingInterceptor(boolean z10, @NotNull Collection<String> keysToFilter, @NotNull Logger logger) {
        this(z10, keysToFilter, logger, new DefaultLoggingPrefixer());
        Intrinsics.checkNotNullParameter(keysToFilter, "keysToFilter");
        Intrinsics.checkNotNullParameter(logger, "logger");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoggingInterceptor(boolean z10, @NotNull Logger logger, @NotNull LoggingPrefixer loggingPrefixer) {
        this(z10, CollectionsKt.listOf((Object[]) new String[]{"access_token", "key", SharedKt.PARAM_CLIENT_SECRET, SessionSQLiteHelper.COLUMN_WEBVIEW_AT, SessionSQLiteHelper.COLUMN_WEBVIEW_RT, "exchange_token", "exchange_tokens", "common_token"}), logger, loggingPrefixer);
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(loggingPrefixer, "loggingPrefixer");
    }
}
