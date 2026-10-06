package ru.mail.data.cmd.server.summarize;

import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.logic.content.DataManager;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.util.log.Logger;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002*+B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ \u0010\u001a\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@¢\u0006\u0002\u0010!J \u0010\"\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0082@¢\u0006\u0002\u0010\u001fJ\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001cH\u0002J\u001e\u0010&\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006,"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeRepository;", "", "interceptor", "Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor;", "dataManager", "Lru/mail/logic/content/DataManager;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "moshi", "Lcom/squareup/moshi/Moshi;", "log", "Lru/mail/util/log/Logger;", "configuration", "Lru/mail/mailapp/DTOConfiguration$Config$Summarize;", "<init>", "(Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor;Lru/mail/logic/content/DataManager;Lkotlinx/coroutines/CoroutineDispatcher;Lcom/squareup/moshi/Moshi;Lru/mail/util/log/Logger;Lru/mail/mailapp/DTOConfiguration$Config$Summarize;)V", "accumulatedChunk", "Lru/mail/data/cmd/server/summarize/SummarizeChunkDto;", "resultSummarize", "Lru/mail/data/cmd/server/summarize/Summarize;", "_summarizeChunkFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "summarizeChunkFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getSummarizeChunkFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "loadSummarize", "messageId", "", HTTP.CHUNK_CODING, "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadSummarizeLocal", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadSummarizeRemote", "onChunkReceivedInternal", "", "chunkJson", "updateLocalSummarize", "rate", "Lru/mail/data/cmd/server/summarize/SummarizeRate;", "(Ljava/lang/String;Lru/mail/data/cmd/server/summarize/SummarizeRate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Callback", "DbCallback", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SummarizeRepository {

    @NotNull
    private final MutableSharedFlow<SummarizeChunkDto> _summarizeChunkFlow;

    @NotNull
    private SummarizeChunkDto accumulatedChunk;

    @NotNull
    private final DTOConfiguration.Config.Summarize configuration;

    @NotNull
    private final DataManager dataManager;

    @NotNull
    private final SummarizeChunkInterceptor interceptor;

    @NotNull
    private final CoroutineDispatcher ioDispatcher;

    @NotNull
    private final Logger log;

    @NotNull
    private final Moshi moshi;

    @Nullable
    private Summarize resultSummarize;

    @NotNull
    private final SharedFlow<SummarizeChunkDto> summarizeChunkFlow;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J&\u0010\u0006\u001a\u00020\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeRepository$Callback;", "", "onSummarizeLoaded", "", "json", "", BatchApiRequest.FIELD_NAME_ON_ERROR, "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Callback {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
        }

        static /* synthetic */ void onError$default(Callback callback, Exception exc, String str, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onError");
            }
            if ((i10 & 1) != 0) {
                exc = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            callback.onError(exc, str);
        }

        void onError(@Nullable Exception e10, @Nullable String message);

        void onSummarizeLoaded(@NotNull String json);
    }

    /* JADX INFO: renamed from: ru.mail.data.cmd.server.summarize.SummarizeRepository$loadSummarize$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lru/mail/data/cmd/server/summarize/Summarize;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.data.cmd.server.summarize.SummarizeRepository$loadSummarize$2", f = "SummarizeRepository.kt", i = {1}, l = {35, 41}, m = "invokeSuspend", n = {MailMessageContent.COL_NAME_SUMMARIZE}, s = {"L$0"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Summarize>, Object> {
        final /* synthetic */ boolean $chunked;
        final /* synthetic */ String $messageId;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, boolean z10, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$messageId = str;
            this.$chunked = z10;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SummarizeRepository.this.new AnonymousClass2(this.$messageId, this.$chunked, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x006b, code lost:
        
            if (r8 == r0) goto L19;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 1
                r3 = 2
                r4 = 0
                if (r1 == 0) goto L23
                if (r1 == r2) goto L1f
                if (r1 != r3) goto L17
                java.lang.Object r0 = r7.L$0
                ru.mail.data.cmd.server.summarize.Summarize r0 = (ru.mail.data.cmd.server.summarize.Summarize) r0
                kotlin.ResultKt.throwOnFailure(r8)
                goto L6e
            L17:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1f:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L33
            L23:
                kotlin.ResultKt.throwOnFailure(r8)
                ru.mail.data.cmd.server.summarize.SummarizeRepository r8 = ru.mail.data.cmd.server.summarize.SummarizeRepository.this
                java.lang.String r1 = r7.$messageId
                r7.label = r2
                java.lang.Object r8 = ru.mail.data.cmd.server.summarize.SummarizeRepository.access$loadSummarizeLocal(r8, r1, r7)
                if (r8 != r0) goto L33
                goto L6d
            L33:
                ru.mail.data.cmd.server.summarize.Summarize r8 = (ru.mail.data.cmd.server.summarize.Summarize) r8
                if (r8 == 0) goto L3d
                ru.mail.data.cmd.server.summarize.SummarizeRepository r0 = ru.mail.data.cmd.server.summarize.SummarizeRepository.this
                ru.mail.data.cmd.server.summarize.SummarizeRepository.access$setResultSummarize$p(r0, r8)
                return r8
            L3d:
                ru.mail.data.cmd.server.summarize.SummarizeRepository r1 = ru.mail.data.cmd.server.summarize.SummarizeRepository.this
                ru.mail.util.log.Logger r1 = ru.mail.data.cmd.server.summarize.SummarizeRepository.access$getLog$p(r1)
                java.lang.String r2 = r7.$messageId
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "summarize loading for "
                r5.append(r6)
                r5.append(r2)
                java.lang.String r2 = r5.toString()
                ru.mail.util.log.Logger.d$default(r1, r2, r4, r3, r4)
                ru.mail.data.cmd.server.summarize.SummarizeRepository r1 = ru.mail.data.cmd.server.summarize.SummarizeRepository.this
                java.lang.String r2 = r7.$messageId
                boolean r5 = r7.$chunked
                java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                r7.L$0 = r8
                r7.label = r3
                java.lang.Object r8 = ru.mail.data.cmd.server.summarize.SummarizeRepository.access$loadSummarizeRemote(r1, r2, r5, r7)
                if (r8 != r0) goto L6e
            L6d:
                return r0
            L6e:
                java.lang.String r8 = (java.lang.String) r8
                if (r8 != 0) goto L73
                return r4
            L73:
                ru.mail.data.cmd.server.summarize.Summarize r0 = new ru.mail.data.cmd.server.summarize.Summarize
                r0.<init>(r8, r4, r3, r4)
                ru.mail.data.cmd.server.summarize.SummarizeRepository r8 = ru.mail.data.cmd.server.summarize.SummarizeRepository.this
                java.lang.String r1 = r7.$messageId
                ru.mail.data.cmd.server.summarize.SummarizeRepository.access$setResultSummarize$p(r8, r0)
                com.squareup.moshi.Moshi r2 = ru.mail.data.cmd.server.summarize.SummarizeRepository.access$getMoshi$p(r8)
                java.lang.Class<ru.mail.data.cmd.server.summarize.Summarize> r3 = ru.mail.data.cmd.server.summarize.Summarize.class
                com.squareup.moshi.JsonAdapter r2 = r2.adapter(r3)
                java.lang.String r2 = r2.toJson(r0)
                ru.mail.logic.content.DataManager r3 = ru.mail.data.cmd.server.summarize.SummarizeRepository.access$getDataManager$p(r8)
                ru.mail.data.cmd.server.summarize.SummarizeRepository$loadSummarize$2$1$1 r4 = new ru.mail.data.cmd.server.summarize.SummarizeRepository$loadSummarize$2$1$1
                r4.<init>()
                r3.saveSummarize(r1, r2, r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.data.cmd.server.summarize.SummarizeRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Summarize> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.data.cmd.server.summarize.SummarizeRepository$loadSummarizeRemote$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.data.cmd.server.summarize.SummarizeRepository", f = "SummarizeRepository.kt", i = {0, 0}, l = {94}, m = "loadSummarizeRemote", n = {"messageId", HTTP.CHUNK_CODING}, s = {"L$0", "Z$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SummarizeRepository.this.loadSummarizeRemote(null, false, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.data.cmd.server.summarize.SummarizeRepository$updateLocalSummarize$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lru/mail/data/cmd/server/summarize/Summarize;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.data.cmd.server.summarize.SummarizeRepository$updateLocalSummarize$2", f = "SummarizeRepository.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C20742 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Summarize>, Object> {
        final /* synthetic */ String $messageId;
        final /* synthetic */ SummarizeRate $rate;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C20742(String str, SummarizeRate summarizeRate, Continuation<? super C20742> continuation) {
            super(2, continuation);
            this.$messageId = str;
            this.$rate = summarizeRate;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SummarizeRepository.this.new C20742(this.$messageId, this.$rate, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            Logger.d$default(SummarizeRepository.this.log, "summarize update summarize for " + this.$messageId + " with rate=" + this.$rate, null, 2, null);
            final SummarizeRepository summarizeRepository = SummarizeRepository.this;
            SummarizeRate summarizeRate = this.$rate;
            final String str = this.$messageId;
            this.L$0 = summarizeRepository;
            this.L$1 = summarizeRate;
            this.L$2 = str;
            this.label = 1;
            final SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(this));
            final Summarize summarize = summarizeRepository.resultSummarize;
            if (summarize != null) {
                final Summarize summarize2 = summarize.getRate() == summarizeRate ? new Summarize(summarize.getText(), null) : new Summarize(summarize.getText(), summarizeRate);
                summarizeRepository.resultSummarize = summarize2;
                summarizeRepository.dataManager.saveSummarize(str, summarizeRepository.moshi.adapter(Summarize.class).toJson(summarize2), new DbCallback() { // from class: ru.mail.data.cmd.server.summarize.SummarizeRepository$updateLocalSummarize$2$1$1$1
                    @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.DbCallback
                    public void onError() {
                        Logger.w$default(summarizeRepository.log, "summarize update error for " + str, null, 2, null);
                        Continuation<Summarize> continuation = safeContinuation;
                        Result.Companion companion = Result.INSTANCE;
                        continuation.resumeWith(Result.m13123constructorimpl(summarize));
                    }

                    @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.DbCallback
                    public /* bridge */ void onLoaded(String str2) {
                        super.onLoaded(str2);
                    }

                    @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.DbCallback
                    public void onSaved() {
                        Logger.d$default(summarizeRepository.log, "summarize updated for " + str, null, 2, null);
                        Continuation<Summarize> continuation = safeContinuation;
                        Result.Companion companion = Result.INSTANCE;
                        continuation.resumeWith(Result.m13123constructorimpl(summarize2));
                    }
                });
            }
            Object orThrow = safeContinuation.getOrThrow();
            if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            return orThrow == coroutine_suspended ? coroutine_suspended : orThrow;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Summarize> continuation) {
            return ((C20742) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public SummarizeRepository(@NotNull SummarizeChunkInterceptor interceptor, @NotNull DataManager dataManager, @NotNull CoroutineDispatcher ioDispatcher, @NotNull Moshi moshi, @NotNull Logger log, @NotNull DTOConfiguration.Config.Summarize configuration) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(ioDispatcher, "ioDispatcher");
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        Intrinsics.checkNotNullParameter(log, "log");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.interceptor = interceptor;
        this.dataManager = dataManager;
        this.ioDispatcher = ioDispatcher;
        this.moshi = moshi;
        this.log = log;
        this.configuration = configuration;
        this.accumulatedChunk = new SummarizeChunkDto("", false);
        MutableSharedFlow<SummarizeChunkDto> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_LATEST, 2, null);
        this._summarizeChunkFlow = mutableSharedFlowMutableSharedFlow$default;
        this.summarizeChunkFlow = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadSummarizeLocal(final String str, Continuation<? super Summarize> continuation) {
        final SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        this.dataManager.loadSummarizeDb(str, new DbCallback() { // from class: ru.mail.data.cmd.server.summarize.SummarizeRepository$loadSummarizeLocal$2$1
            @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.DbCallback
            public void onError() {
                Logger.d$default(this.this$0.log, "summarize local load error for " + str, null, 2, null);
                safeContinuation.resumeWith(Result.m13123constructorimpl(null));
            }

            @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.DbCallback
            public void onLoaded(String summarizeJson) {
                if (summarizeJson == null) {
                    Logger.d$default(this.this$0.log, "no local summarize for " + str, null, 2, null);
                    safeContinuation.resumeWith(Result.m13123constructorimpl(null));
                    return;
                }
                Logger.d$default(this.this$0.log, "summarize loaded locally for " + str, null, 2, null);
                safeContinuation.resumeWith(Result.m13123constructorimpl((Summarize) this.this$0.moshi.adapter(Summarize.class).fromJson(summarizeJson)));
            }

            @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.DbCallback
            public /* bridge */ void onSaved() {
                super.onSaved();
            }
        });
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object loadSummarizeRemote(final String str, boolean z10, Continuation<? super String> continuation) {
        AnonymousClass1 anonymousClass1;
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
        Object orThrow = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(orThrow);
            this.interceptor.setSummarizeChunkReceiver(new SummarizeChunkInterceptor.SummarizeChunkReceiver() { // from class: ru.mail.data.cmd.server.summarize.SummarizeRepository.loadSummarizeRemote.2
                @Override // ru.mail.data.cmd.server.summarize.SummarizeChunkInterceptor.SummarizeChunkReceiver
                public void onChunkReceived(String chunkJson) {
                    Intrinsics.checkNotNullParameter(chunkJson, "chunkJson");
                    SummarizeRepository.this.onChunkReceivedInternal(chunkJson);
                }

                @Override // ru.mail.data.cmd.server.summarize.SummarizeChunkInterceptor.SummarizeChunkReceiver
                public String tag() {
                    return "Summarize-" + str;
                }
            });
            anonymousClass1.L$0 = str;
            anonymousClass1.Z$0 = z10;
            anonymousClass1.label = 1;
            final SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(anonymousClass1));
            this.dataManager.loadSummarize(str, z10, this.configuration.getGroup(), this.configuration.getRetryCount(), this.configuration.getRequestTimeout(), new Callback() { // from class: ru.mail.data.cmd.server.summarize.SummarizeRepository$loadSummarizeRemote$3$1
                @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.Callback
                public void onError(Exception e10, String message) {
                    Logger.d$default(this.this$0.log, "summarize remote load error", null, 2, null);
                    Continuation<String> continuation2 = safeContinuation;
                    Result.Companion companion = Result.INSTANCE;
                    if (e10 == null) {
                        e10 = new Exception(message);
                    }
                    continuation2.resumeWith(Result.m13123constructorimpl(ResultKt.createFailure(e10)));
                }

                @Override // ru.mail.data.cmd.server.summarize.SummarizeRepository.Callback
                public void onSummarizeLoaded(String json) {
                    SummarizeDto body;
                    String summary;
                    Intrinsics.checkNotNullParameter(json, "json");
                    String string = null;
                    Logger.d$default(this.this$0.log, "summarize received for " + str + StringUtils.SPACE + json, null, 2, null);
                    Continuation<String> continuation2 = safeContinuation;
                    if (StringsKt.contains$default((CharSequence) json, (CharSequence) "delta", false, 2, (Object) null)) {
                        string = this.this$0.accumulatedChunk.getDelta();
                    } else {
                        SummarizeFullDto summarizeFullDto = (SummarizeFullDto) this.this$0.moshi.adapter(SummarizeFullDto.class).fromJson(json);
                        if (summarizeFullDto != null && (body = summarizeFullDto.getBody()) != null && (summary = body.getSummary()) != null) {
                            string = StringsKt.trim((CharSequence) summary).toString();
                        }
                    }
                    continuation2.resumeWith(Result.m13123constructorimpl(string));
                }
            });
            orThrow = safeContinuation.getOrThrow();
            if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(anonymousClass1);
            }
            if (orThrow == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(orThrow);
        }
        this.interceptor.setSummarizeChunkReceiver(null);
        return orThrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onChunkReceivedInternal(String chunkJson) {
        SummarizeChunkDto summarizeChunkDto;
        Logger.d$default(this.log, "summarize chunk received " + chunkJson, null, 2, null);
        try {
            Object objFromJson = this.moshi.adapter(SummarizeChunkedDto.class).fromJson(chunkJson);
            Intrinsics.checkNotNull(objFromJson);
            summarizeChunkDto = ((SummarizeChunkedDto) objFromJson).getBody();
        } catch (Exception unused) {
            Logger.w$default(this.log, "wrong chunk format, happens on stream finish", null, 2, null);
            summarizeChunkDto = new SummarizeChunkDto("", true);
        }
        SummarizeChunkDto summarizeChunkDto2 = new SummarizeChunkDto(StringsKt.trimStart((CharSequence) this.accumulatedChunk.getDelta()).toString() + summarizeChunkDto.getDelta(), summarizeChunkDto.getFinished());
        this.accumulatedChunk = summarizeChunkDto2;
        this._summarizeChunkFlow.tryEmit(summarizeChunkDto2);
    }

    @NotNull
    public final SharedFlow<SummarizeChunkDto> getSummarizeChunkFlow() {
        return this.summarizeChunkFlow;
    }

    @Nullable
    public final Object loadSummarize(@NotNull String str, boolean z10, @NotNull Continuation<? super Summarize> continuation) {
        return BuildersKt.withContext(this.ioDispatcher, new AnonymousClass2(str, z10, null), continuation);
    }

    @Nullable
    public final Object updateLocalSummarize(@NotNull String str, @NotNull SummarizeRate summarizeRate, @NotNull Continuation<? super Summarize> continuation) {
        return BuildersKt.withContext(this.ioDispatcher, new C20742(str, summarizeRate, null), continuation);
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0012\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeRepository$DbCallback;", "", BatchApiRequest.FIELD_NAME_ON_ERROR, "", "onSaved", "onLoaded", "summarizeJson", "", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DbCallback {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void onLoaded(@NotNull DbCallback dbCallback, @Nullable String str) {
                DbCallback.super.onLoaded(str);
            }

            @Deprecated
            public static void onSaved(@NotNull DbCallback dbCallback) {
                DbCallback.super.onSaved();
            }
        }

        void onError();

        default void onSaved() {
        }

        default void onLoaded(@Nullable String summarizeJson) {
        }
    }
}
