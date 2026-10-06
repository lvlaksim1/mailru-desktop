package ru.mail.smoothie.web.auth;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import ru.mail.kit.analytics.Analytics;
import ru.mail.kit.auth.AuthProvider;
import ru.mail.kit.auth.info.AccessTokenAuthInfo;
import ru.mail.util.log.Logger;
import ru.mail.web.wrapper.OauthWebViewWrapper;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lru/mail/smoothie/web/auth/MultiAccountAuthCallback;", "Lru/mail/kit/auth/AuthProvider$AuthCallback;", "Lru/mail/kit/auth/info/AccessTokenAuthInfo;", "webViewWrapper", "Lru/mail/web/wrapper/OauthWebViewWrapper;", "analytics", "Lru/mail/kit/analytics/Analytics;", "accountsToWait", "", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/web/wrapper/OauthWebViewWrapper;Lru/mail/kit/analytics/Analytics;ILru/mail/util/log/Logger;)V", "callbackLogger", "accounts", "Ljava/util/concurrent/ConcurrentHashMap;", "", "accountToWaitLeft", "Ljava/util/concurrent/atomic/AtomicInteger;", "someRequestsSucceeded", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mainScope", "Lkotlinx/coroutines/CoroutineScope;", BatchApiRequest.FIELD_NAME_ON_ERROR, "", "onSuccess", "authInfo", "login", "getEmailsTokens", "Companion", "web-auth_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMultiAccountAuthCallback.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiAccountAuthCallback.kt\nru/mail/smoothie/web/auth/MultiAccountAuthCallback\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,79:1\n126#2:80\n153#2,3:81\n*S KotlinDebug\n*F\n+ 1 MultiAccountAuthCallback.kt\nru/mail/smoothie/web/auth/MultiAccountAuthCallback\n*L\n52#1:80\n52#1:81,3\n*E\n"})
public final class MultiAccountAuthCallback implements AuthProvider.AuthCallback<AccessTokenAuthInfo> {

    @NotNull
    public static final String ACCESS_TOKEN_PARAM = "access_token";

    @NotNull
    public static final String EMAIL_PARAM = "email";

    @NotNull
    private final AtomicInteger accountToWaitLeft;

    @NotNull
    private final ConcurrentHashMap<String, AccessTokenAuthInfo> accounts;

    @NotNull
    private final Analytics analytics;

    @NotNull
    private final Logger callbackLogger;

    @NotNull
    private final CoroutineScope mainScope;

    @NotNull
    private final AtomicBoolean someRequestsSucceeded;

    @NotNull
    private final OauthWebViewWrapper webViewWrapper;

    /* JADX INFO: renamed from: ru.mail.smoothie.web.auth.MultiAccountAuthCallback$onError$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.smoothie.web.auth.MultiAccountAuthCallback$onError$1", f = "MultiAccountAuthCallback.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MultiAccountAuthCallback.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MultiAccountAuthCallback.this.webViewWrapper.setOauthTokenFailed();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.smoothie.web.auth.MultiAccountAuthCallback$onSuccess$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.smoothie.web.auth.MultiAccountAuthCallback$onSuccess$2", f = "MultiAccountAuthCallback.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MultiAccountAuthCallback.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MultiAccountAuthCallback.this.webViewWrapper.setOauthCredentials(MultiAccountAuthCallback.this.getEmailsTokens());
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public MultiAccountAuthCallback(@NotNull OauthWebViewWrapper webViewWrapper, @NotNull Analytics analytics, int i10, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(webViewWrapper, "webViewWrapper");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.webViewWrapper = webViewWrapper;
        this.analytics = analytics;
        this.callbackLogger = logger.createLogger("MultiAccountAuthCallback");
        this.accounts = new ConcurrentHashMap<>();
        this.accountToWaitLeft = new AtomicInteger(i10);
        this.someRequestsSucceeded = new AtomicBoolean(false);
        this.mainScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getEmailsTokens() {
        final JsonArray jsonArray = new JsonArray();
        ConcurrentHashMap<String, AccessTokenAuthInfo> concurrentHashMap = this.accounts;
        final Function2 function2 = new Function2() { // from class: ru.mail.smoothie.web.auth.a
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MultiAccountAuthCallback.getEmailsTokens$lambda$0(jsonArray, (String) obj, (AccessTokenAuthInfo) obj2);
            }
        };
        concurrentHashMap.forEach(new BiConsumer() { // from class: ru.mail.smoothie.web.auth.b
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                function2.invoke(obj, obj2);
            }
        });
        String string = jsonArray.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getEmailsTokens$lambda$0(JsonArray jsonArray, String login, AccessTokenAuthInfo authInfo) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(authInfo, "authInfo");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", login);
        jsonObject.addProperty("access_token", authInfo.getAccessToken());
        jsonArray.add(jsonObject);
        return Unit.INSTANCE;
    }

    @Override // ru.mail.kit.auth.AuthProvider.AuthCallback
    public void onError() {
        Logger.d$default(this.callbackLogger, "On error multiaccount callback", null, 2, null);
        boolean z10 = this.accountToWaitLeft.decrementAndGet() == 0;
        boolean z11 = this.someRequestsSucceeded.get();
        if (!z10 || z11) {
            return;
        }
        Analytics.send$default(this.analytics, "MultiAccountAuthCallback_Failed_Event", null, 2, null);
        BuildersKt__Builders_commonKt.launch$default(this.mainScope, null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // ru.mail.kit.auth.AuthProvider.AuthCallback
    public void onSuccess(@NotNull AccessTokenAuthInfo authInfo, @NotNull String login) {
        Intrinsics.checkNotNullParameter(authInfo, "authInfo");
        Intrinsics.checkNotNullParameter(login, "login");
        Logger.d$default(this.callbackLogger, "Success for " + login, null, 2, null);
        this.someRequestsSucceeded.set(true);
        this.accounts.put(login, authInfo);
        if (this.accountToWaitLeft.decrementAndGet() == 0) {
            Logger logger = this.callbackLogger;
            ConcurrentHashMap<String, AccessTokenAuthInfo> concurrentHashMap = this.accounts;
            ArrayList arrayList = new ArrayList(concurrentHashMap.size());
            Iterator<Map.Entry<String, AccessTokenAuthInfo>> it = concurrentHashMap.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getKey());
            }
            Logger.d$default(logger, "Tokens for accounts: " + arrayList + " succeeded", null, 2, null);
            BuildersKt__Builders_commonKt.launch$default(this.mainScope, null, null, new AnonymousClass2(null), 3, null);
        }
    }
}
