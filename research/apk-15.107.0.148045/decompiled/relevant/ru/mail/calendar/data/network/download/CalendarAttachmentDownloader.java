package ru.mail.calendar.data.network.download;

import android.accounts.Account;
import android.content.Context;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;
import okhttp3.ResponseBody;
import org.apache.http.HttpHeaders;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.mail.auth.am.AccountManagerDelegate;
import ru.mail.calendar.domain.repository.CalendarAttachmentRepository;
import ru.mail.calendar.domain.utils.CalendarAttachmentUtils;
import ru.mail.cloud.app.network.CloudHttpClient;
import ru.mail.cloud.presentationlayer.CloudNavigator;
import ru.mail.download.facade.models.CalendarOfflineAttachmentPayload;
import ru.mail.download.facade.models.OfflineAttachmentPayload;
import ru.mail.download.facade.models.OfflineAttachmentPayloadKt;
import ru.mail.file.shrinker.FileShrinker;
import ru.mail.file.shrinker.FileToShrink;
import ru.mail.kit.auth.AuthInfoIOException;
import ru.mail.kit.auth.AuthManager;
import ru.mail.kit.auth.account.HostAccountInfo;
import ru.mail.kit.auth.helpers.AuthInfoProvider;
import ru.mail.kit.auth.info.AccessTokenAuthInfo;
import ru.mail.util.log.Logger;
import ru.ok.android.sdk.OkListenerKt;
import vk.team.cloud.download.api.DownloadObjectParams;
import vk.team.cloud.download.api.download.Downloader;
import vk.team.cloud.download.api.download.UpdateDownloadInterface;
import vk.team.cloud.download.api.progress.observe.FileDownloadError;
import vk.team.cloud.download.api.progress.observe.FileDownloadInProgress;
import vk.team.cloud.download.api.progress.observe.FileDownloadState;
import vk.team.cloud.download.api.progress.observe.FileDownloaded;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 N2\u00020\u0001:\u0001NBY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0096@¢\u0006\u0002\u0010!J&\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020#H\u0082@¢\u0006\u0002\u0010'J\u001e\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010,J\u001a\u0010-\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J\u0010\u00100\u001a\u00020#2\u0006\u00101\u001a\u00020\u000fH\u0002J.\u00102\u001a\u00020\u001c2\u0006\u00103\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205H\u0082@¢\u0006\u0002\u00107JN\u00108\u001a\b\u0012\u0004\u0012\u00020\u001c092\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010:\u001a\u0002052\u0006\u0010;\u001a\u0002052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020=2\u0006\u0010&\u001a\u00020#H\u0082@¢\u0006\u0004\b>\u0010?J&\u0010@\u001a\u0002052\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020#H\u0082@¢\u0006\u0002\u0010FJ$\u0010G\u001a\u00020\u001c2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020B0I2\u0006\u0010<\u001a\u00020=H\u0082@¢\u0006\u0002\u0010JJ\b\u0010K\u001a\u00020\u000fH\u0002J\u000e\u0010L\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010MR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lru/mail/calendar/data/network/download/CalendarAttachmentDownloader;", "Lvk/team/cloud/download/api/download/Downloader;", "retrofit", "Lretrofit2/Retrofit;", "logger", "Lru/mail/util/log/Logger;", "context", "Landroid/content/Context;", "accountManagerDelegate", "Lru/mail/auth/am/AccountManagerDelegate;", "authManager", "Lru/mail/kit/auth/AuthManager;", "authInfoProvider", "Lru/mail/kit/auth/helpers/AuthInfoProvider;", "clientId", "", "userAgent", "attachmentRepository", "Lru/mail/calendar/domain/repository/CalendarAttachmentRepository;", "fileShrinker", "Lru/mail/file/shrinker/FileShrinker;", "<init>", "(Lretrofit2/Retrofit;Lru/mail/util/log/Logger;Landroid/content/Context;Lru/mail/auth/am/AccountManagerDelegate;Lru/mail/kit/auth/AuthManager;Lru/mail/kit/auth/helpers/AuthInfoProvider;Ljava/lang/String;Ljava/lang/String;Lru/mail/calendar/domain/repository/CalendarAttachmentRepository;Lru/mail/file/shrinker/FileShrinker;)V", "log", "downloadApi", "Lru/mail/calendar/data/network/download/CalendarAttachmentDownloadApi;", "kotlin.jvm.PlatformType", "startDownload", "", "updateDownloadInterface", "Lvk/team/cloud/download/api/download/UpdateDownloadInterface;", "fileDownloadState", "Lvk/team/cloud/download/api/progress/observe/FileDownloadState;", "(Lvk/team/cloud/download/api/download/UpdateDownloadInterface;Lvk/team/cloud/download/api/progress/observe/FileDownloadState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkIfFileChanged", "", "downloadUrl", "savedEtag", "requiresAuth", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDownloadedFile", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "Lru/mail/download/facade/models/CalendarOfflineAttachmentPayload;", "destinationPath", "(Lru/mail/download/facade/models/CalendarOfflineAttachmentPayload;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processErrorFile", OkListenerKt.KEY_EXCEPTION, "", "isAppStoragePath", "path", "addFileToShrinker", "cacheKey", "sizeBytes", "", "sourceDate", "(Ljava/lang/String;Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadWithResumeSupport", "Lkotlin/Result;", "alreadyDownloaded", "expectedFileLength", "params", "Lvk/team/cloud/download/api/DownloadObjectParams;", "downloadWithResumeSupport-eH_QyT8", "(Ljava/lang/String;Ljava/lang/String;JJLvk/team/cloud/download/api/download/UpdateDownloadInterface;Lvk/team/cloud/download/api/DownloadObjectParams;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeChunkToFile", "body", "Lokhttp3/ResponseBody;", "destinationFile", "Ljava/io/File;", "append", "(Lokhttp3/ResponseBody;Ljava/io/File;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEtagIfNeeded", "response", "Lretrofit2/Response;", "(Lretrofit2/Response;Lvk/team/cloud/download/api/DownloadObjectParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getToken", "getMailToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "calendar-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCalendarAttachmentDownloader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CalendarAttachmentDownloader.kt\nru/mail/calendar/data/network/download/CalendarAttachmentDownloader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n*L\n1#1,609:1\n1#2:610\n1761#3,3:611\n375#4:614\n*S KotlinDebug\n*F\n+ 1 CalendarAttachmentDownloader.kt\nru/mail/calendar/data/network/download/CalendarAttachmentDownloader\n*L\n347#1:611,3\n529#1:614\n*E\n"})
public final class CalendarAttachmentDownloader implements Downloader {
    private static final int BUFFER_SIZE = 8192;
    private static final long CHUNK_SIZE_BYTES = 10485760;
    private static final int HTTP_FORBIDDEN = 403;
    private static final int HTTP_OK_END = 299;
    private static final int HTTP_OK_START = 200;
    private static final int HTTP_PARTIAL_CONTENT = 206;
    private static final int HTTP_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    private static final int HTTP_REQUEST_TIMEOUT = 408;
    private static final int HTTP_SERVER_ERROR_END = 599;
    private static final int HTTP_SERVER_ERROR_START = 500;
    private static final int HTTP_TOO_MANY_REQUESTS = 429;
    private static final int HTTP_UNAUTHORIZED = 401;
    private static final long PROGRESS_UPDATE_INTERVAL_MS = 500;

    @NotNull
    private final AccountManagerDelegate accountManagerDelegate;

    @NotNull
    private final CalendarAttachmentRepository attachmentRepository;

    @NotNull
    private final AuthInfoProvider authInfoProvider;

    @NotNull
    private final AuthManager authManager;

    @NotNull
    private final String clientId;

    @NotNull
    private final Context context;
    private final CalendarAttachmentDownloadApi downloadApi;

    @NotNull
    private final FileShrinker fileShrinker;

    @NotNull
    private final Logger log;

    @NotNull
    private final Logger logger;

    @NotNull
    private final String userAgent;

    @NotNull
    private static final Map<String, String> defaultHeaders = MapsKt.mapOf(TuplesKt.to("Accept-Encoding", "identity"));

    /* JADX INFO: renamed from: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$addFileToShrinker$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.calendar.data.network.download.CalendarAttachmentDownloader", f = "CalendarAttachmentDownloader.kt", i = {0, 0, 0, 0, 0}, l = {366}, m = "addFileToShrinker", n = {"cacheKey", "destinationPath", "fileToShrink", "sizeBytes", "sourceDate"}, s = {"L$0", "L$1", "L$2", "J$0", "J$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
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
            return CalendarAttachmentDownloader.this.addFileToShrinker(null, null, 0L, 0L, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$checkIfFileChanged$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$checkIfFileChanged$2", f = "CalendarAttachmentDownloader.kt", i = {1}, l = {204, 208}, m = "invokeSuspend", n = {CloudHttpClient.AUTHORIZATION}, s = {"L$0"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ String $downloadUrl;
        final /* synthetic */ boolean $requiresAuth;
        final /* synthetic */ String $savedEtag;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, boolean z10, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$downloadUrl = str;
            this.$requiresAuth = z10;
            this.$savedEtag = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalendarAttachmentDownloader.this.new AnonymousClass2(this.$downloadUrl, this.$requiresAuth, this.$savedEtag, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x008f, code lost:
        
            if (r14 == r2) goto L28;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 419
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$getMailToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.calendar.data.network.download.CalendarAttachmentDownloader", f = "CalendarAttachmentDownloader.kt", i = {0, 0}, l = {586}, m = "getMailToken", n = {"hostAccount", "account"}, s = {"L$0", "L$1"}, v = 1)
    static final class C18511 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C18511(Continuation<? super C18511> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalendarAttachmentDownloader.this.getMailToken(this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$processDownloadedFile$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$processDownloadedFile$2", f = "CalendarAttachmentDownloader.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}, l = {285, 306, 311}, m = "invokeSuspend", n = {"$this$withContext", "file", "cacheKey", "$this$withContext", "file", "cacheKey", CloudNavigator.PARAMS_FILE_NAME, "record", "$this$invokeSuspend_u24lambda_u240", "fileSize", "$i$a$-runCatching-CalendarAttachmentDownloader$processDownloadedFile$2$1", "$this$withContext", "file", "cacheKey", CloudNavigator.PARAMS_FILE_NAME, "record", "$this$invokeSuspend_u24lambda_u240", "fileSize", "$i$a$-runCatching-CalendarAttachmentDownloader$processDownloadedFile$2$1"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$8", "J$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "I$0"}, v = 1)
    static final class C18522 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $destinationPath;
        final /* synthetic */ CalendarOfflineAttachmentPayload $payload;
        int I$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        final /* synthetic */ CalendarAttachmentDownloader this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C18522(CalendarOfflineAttachmentPayload calendarOfflineAttachmentPayload, CalendarAttachmentDownloader calendarAttachmentDownloader, String str, Continuation<? super C18522> continuation) {
            super(2, continuation);
            this.$payload = calendarOfflineAttachmentPayload;
            this.this$0 = calendarAttachmentDownloader;
            this.$destinationPath = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C18522 c18522 = new C18522(this.$payload, this.this$0, this.$destinationPath, continuation);
            c18522.L$0 = obj;
            return c18522;
        }

        /* JADX WARN: Code duplicated, block: B:42:0x01a7 A[Catch: all -> 0x0066, TRY_LEAVE, TryCatch #1 {all -> 0x0066, blocks: (B:15:0x0061, B:40:0x0183, B:42:0x01a7), top: B:58:0x0061 }] */
        /* JADX WARN: Code duplicated, block: B:47:0x01e4 A[Catch: all -> 0x0032, TryCatch #0 {all -> 0x0032, blocks: (B:8:0x002d, B:48:0x0201, B:44:0x01dd, B:47:0x01e4, B:36:0x0144), top: B:56:0x0010 }] */
        /* JADX WARN: Code duplicated, block: B:53:0x021a  */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x01e1, code lost:
        
            if (r0 == r8) goto L46;
         */
        /* JADX WARN: Instruction removed from duplicated block: B:47:0x01e4, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:53:0x021a, please report this as an issue */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v16 */
        /* JADX WARN: Type inference failed for: r1v18 */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v20 */
        /* JADX WARN: Type inference failed for: r1v27 */
        /* JADX WARN: Type inference failed for: r1v28 */
        /* JADX WARN: Type inference failed for: r1v29 */
        /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v30 */
        /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.StringBuilder] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r30) {
            /*
                Method dump skipped, instruction units count: 565
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader.C18522.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C18522) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$saveEtagIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.calendar.data.network.download.CalendarAttachmentDownloader", f = "CalendarAttachmentDownloader.kt", i = {0, 0, 0, 0, 0}, l = {556}, m = "saveEtagIfNeeded", n = {"response", "params", "etag", "calendarPayload", "cacheKey"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
    static final class C18531 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C18531(Continuation<? super C18531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalendarAttachmentDownloader.this.saveEtagIfNeeded(null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader$startDownload$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.calendar.data.network.download.CalendarAttachmentDownloader", f = "CalendarAttachmentDownloader.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}, l = {80, 93, 97, 110, 125, 142, 160, 169, 178}, m = "startDownload", n = {"updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "savedEtag", "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "savedEtag", "shouldDownload", "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "savedEtag", "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "savedEtag", "requiresAuth", "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "savedEtag", "result", "destinationPath", "requiresAuth", "actualFileSize", "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "savedEtag", "result", "destinationPath", "requiresAuth", "actualFileSize", "updateDownloadInterface", "fileDownloadState", "params", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "cacheKey", "savedEtag", "result", "error", "requiresAuth", "actualDownloadedLength"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "J$0"}, v = 1)
    static final class C18541 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C18541(Continuation<? super C18541> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalendarAttachmentDownloader.this.startDownload(null, null, this);
        }
    }

    public CalendarAttachmentDownloader(@NotNull Retrofit retrofit, @NotNull Logger logger, @ApplicationContext @NotNull Context context, @NotNull AccountManagerDelegate accountManagerDelegate, @NotNull AuthManager authManager, @NotNull AuthInfoProvider authInfoProvider, @NotNull String clientId, @NotNull String userAgent, @NotNull CalendarAttachmentRepository attachmentRepository, @NotNull FileShrinker fileShrinker) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountManagerDelegate, "accountManagerDelegate");
        Intrinsics.checkNotNullParameter(authManager, "authManager");
        Intrinsics.checkNotNullParameter(authInfoProvider, "authInfoProvider");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
        Intrinsics.checkNotNullParameter(attachmentRepository, "attachmentRepository");
        Intrinsics.checkNotNullParameter(fileShrinker, "fileShrinker");
        this.logger = logger;
        this.context = context;
        this.accountManagerDelegate = accountManagerDelegate;
        this.authManager = authManager;
        this.authInfoProvider = authInfoProvider;
        this.clientId = clientId;
        this.userAgent = userAgent;
        this.attachmentRepository = attachmentRepository;
        this.fileShrinker = fileShrinker;
        this.log = logger.createLogger("CalendarAttachmentDownloader");
        this.downloadApi = (CalendarAttachmentDownloadApi) retrofit.create(CalendarAttachmentDownloadApi.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object addFileToShrinker(String str, String str2, long j10, long j11, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        String str3;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(obj);
            FileToShrink fileToShrink = new FileToShrink(str, str2, j10, j11, null, 16, null);
            FileShrinker fileShrinker = this.fileShrinker;
            anonymousClass1.L$0 = str;
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(fileToShrink);
            anonymousClass1.J$0 = j10;
            anonymousClass1.J$1 = j11;
            anonymousClass1.label = 1;
            if (fileShrinker.putFileToShrink(fileToShrink, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            str3 = str;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str3 = (String) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Logger.i$default(this.log, "File added to shrinker: " + str3, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object checkIfFileChanged(String str, String str2, boolean z10, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, z10, str2, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX INFO: renamed from: downloadWithResumeSupport-eH_QyT8, reason: not valid java name */
    public final Object m14890downloadWithResumeSupporteH_QyT8(String str, String str2, long j10, long j11, UpdateDownloadInterface updateDownloadInterface, DownloadObjectParams downloadObjectParams, boolean z10, Continuation<? super Result<Unit>> continuation) {
        CalendarAttachmentDownloader$downloadWithResumeSupport$1 calendarAttachmentDownloader$downloadWithResumeSupport$1;
        CalendarAttachmentDownloader calendarAttachmentDownloader;
        if (continuation instanceof CalendarAttachmentDownloader$downloadWithResumeSupport$1) {
            calendarAttachmentDownloader$downloadWithResumeSupport$1 = (CalendarAttachmentDownloader$downloadWithResumeSupport$1) continuation;
            int i10 = calendarAttachmentDownloader$downloadWithResumeSupport$1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                calendarAttachmentDownloader$downloadWithResumeSupport$1.label = i10 - Integer.MIN_VALUE;
                calendarAttachmentDownloader = this;
            } else {
                calendarAttachmentDownloader = this;
                calendarAttachmentDownloader$downloadWithResumeSupport$1 = new CalendarAttachmentDownloader$downloadWithResumeSupport$1(calendarAttachmentDownloader, continuation);
            }
        } else {
            calendarAttachmentDownloader = this;
            calendarAttachmentDownloader$downloadWithResumeSupport$1 = new CalendarAttachmentDownloader$downloadWithResumeSupport$1(calendarAttachmentDownloader, continuation);
        }
        Object objWithContext = calendarAttachmentDownloader$downloadWithResumeSupport$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = calendarAttachmentDownloader$downloadWithResumeSupport$1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objWithContext);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            CalendarAttachmentDownloader$downloadWithResumeSupport$2 calendarAttachmentDownloader$downloadWithResumeSupport$2 = new CalendarAttachmentDownloader$downloadWithResumeSupport$2(str2, j10, calendarAttachmentDownloader, z10, j11, str, downloadObjectParams, updateDownloadInterface, null);
            calendarAttachmentDownloader$downloadWithResumeSupport$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            calendarAttachmentDownloader$downloadWithResumeSupport$1.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            calendarAttachmentDownloader$downloadWithResumeSupport$1.L$2 = SpillingKt.nullOutSpilledVariable(updateDownloadInterface);
            calendarAttachmentDownloader$downloadWithResumeSupport$1.L$3 = SpillingKt.nullOutSpilledVariable(downloadObjectParams);
            calendarAttachmentDownloader$downloadWithResumeSupport$1.J$0 = j10;
            calendarAttachmentDownloader$downloadWithResumeSupport$1.J$1 = j11;
            calendarAttachmentDownloader$downloadWithResumeSupport$1.Z$0 = z10;
            calendarAttachmentDownloader$downloadWithResumeSupport$1.label = 1;
            objWithContext = BuildersKt.withContext(io2, calendarAttachmentDownloader$downloadWithResumeSupport$2, calendarAttachmentDownloader$downloadWithResumeSupport$1);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objWithContext);
        }
        return ((Result) objWithContext).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object getMailToken(Continuation<? super String> continuation) throws AuthInfoIOException {
        C18511 c18511;
        Object obj;
        if (continuation instanceof C18511) {
            c18511 = (C18511) continuation;
            int i10 = c18511.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c18511.label = i10 - Integer.MIN_VALUE;
            } else {
                c18511 = new C18511(continuation);
            }
        } else {
            c18511 = new C18511(continuation);
        }
        Object objRequireAuthInfo = c18511.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c18511.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objRequireAuthInfo);
            HostAccountInfo activeAccount = this.authManager.getActiveAccount();
            Account[] accounts = this.accountManagerDelegate.getAccounts();
            int length = accounts.length;
            int i12 = 0;
            while (true) {
                obj = null;
                if (i12 >= length) {
                    break;
                }
                Account account = accounts[i12];
                if (Intrinsics.areEqual(account.name, activeAccount != null ? activeAccount.getLogin() : null)) {
                    obj = account;
                    break;
                }
                i12++;
            }
            if (obj == null) {
                throw new IllegalStateException(("Token take fail account invalid " + MailTokenClientIdHolder.INSTANCE + ".value").toString());
            }
            AuthInfoProvider authInfoProvider = this.authInfoProvider;
            c18511.L$0 = SpillingKt.nullOutSpilledVariable(activeAccount);
            c18511.L$1 = SpillingKt.nullOutSpilledVariable(obj);
            c18511.label = 1;
            objRequireAuthInfo = authInfoProvider.requireAuthInfo(c18511);
            if (objRequireAuthInfo == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objRequireAuthInfo);
        }
        String accessToken = ((AccessTokenAuthInfo) objRequireAuthInfo).getAccessToken();
        if (accessToken != null) {
            return accessToken;
        }
        throw new IllegalStateException(("t take fail token is null " + MailTokenClientIdHolder.INSTANCE.getValue()).toString());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final String getToken() {
        Account account;
        HostAccountInfo activeAccount = this.authManager.getActiveAccount();
        Account[] accounts = this.accountManagerDelegate.getAccounts();
        int length = accounts.length;
        int i10 = 0;
        while (true) {
            account = null;
            if (i10 >= length) {
                break;
            }
            Account account2 = accounts[i10];
            if (Intrinsics.areEqual(account2.name, activeAccount != null ? activeAccount.getLogin() : null)) {
                account = account2;
                break;
            }
            i10++;
        }
        if (account == null) {
            throw new IllegalStateException("Token take fail account invalid");
        }
        String userData = this.accountManagerDelegate.getUserData(account, this.clientId + "access_token");
        if (userData != null && userData.length() == 0) {
            throw new IllegalStateException("Token take fail token is empty");
        }
        if (userData != null) {
            return userData;
        }
        throw new IllegalStateException("Token take fail token is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAppStoragePath(String path) {
        String absolutePath;
        String absolutePath2;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(this.context.getFilesDir().getAbsolutePath());
        listCreateListBuilder.add(this.context.getCacheDir().getAbsolutePath());
        File externalFilesDir = this.context.getExternalFilesDir(null);
        if (externalFilesDir != null && (absolutePath2 = externalFilesDir.getAbsolutePath()) != null) {
            listCreateListBuilder.add(absolutePath2);
        }
        File externalCacheDir = this.context.getExternalCacheDir();
        if (externalCacheDir != null && (absolutePath = externalCacheDir.getAbsolutePath()) != null) {
            listCreateListBuilder.add(absolutePath);
        }
        List<String> listBuild = CollectionsKt.build(listCreateListBuilder);
        if ((listBuild instanceof Collection) && listBuild.isEmpty()) {
            return false;
        }
        for (String str : listBuild) {
            Intrinsics.checkNotNull(str);
            if (StringsKt.startsWith$default(path, str, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processDownloadedFile(CalendarOfflineAttachmentPayload calendarOfflineAttachmentPayload, String str, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C18522(calendarOfflineAttachmentPayload, this, str, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    private final void processErrorFile(CalendarOfflineAttachmentPayload payload, Throwable exception) {
        String strOfflineAttachmentIdToCacheKey = CalendarAttachmentUtils.INSTANCE.offlineAttachmentIdToCacheKey(payload.getId());
        Logger.e$default(this.log, "Download failed: " + strOfflineAttachmentIdToCacheKey + ", error=" + (exception != null ? exception.getMessage() : null), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object saveEtagIfNeeded(Response<ResponseBody> response, DownloadObjectParams downloadObjectParams, Continuation<? super Unit> continuation) {
        C18531 c18531;
        String strTrim;
        String str;
        String str2;
        if (continuation instanceof C18531) {
            c18531 = (C18531) continuation;
            int i10 = c18531.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c18531.label = i10 - Integer.MIN_VALUE;
            } else {
                c18531 = new C18531(continuation);
            }
        } else {
            c18531 = new C18531(continuation);
        }
        Object obj = c18531.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c18531.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(obj);
            String str3 = response.headers().get(HttpHeaders.ETAG);
            if (str3 == null || (strTrim = StringsKt.trim(str3, '\"')) == null) {
                return Unit.INSTANCE;
            }
            OfflineAttachmentPayload offlineAttachmentPayloadOrNull = OfflineAttachmentPayloadKt.getOfflineAttachmentPayloadOrNull(downloadObjectParams);
            CalendarOfflineAttachmentPayload calendarOfflineAttachmentPayload = offlineAttachmentPayloadOrNull instanceof CalendarOfflineAttachmentPayload ? (CalendarOfflineAttachmentPayload) offlineAttachmentPayloadOrNull : null;
            if (calendarOfflineAttachmentPayload == null) {
                return Unit.INSTANCE;
            }
            if (calendarOfflineAttachmentPayload.getBypassCache()) {
                Logger.d$default(this.log, "bypassCache=true, skip ETag save", null, 2, null);
                return Unit.INSTANCE;
            }
            String strOfflineAttachmentIdToCacheKey = CalendarAttachmentUtils.INSTANCE.offlineAttachmentIdToCacheKey(calendarOfflineAttachmentPayload.getId());
            CalendarAttachmentRepository calendarAttachmentRepository = this.attachmentRepository;
            c18531.L$0 = SpillingKt.nullOutSpilledVariable(response);
            c18531.L$1 = SpillingKt.nullOutSpilledVariable(downloadObjectParams);
            c18531.L$2 = strTrim;
            c18531.L$3 = SpillingKt.nullOutSpilledVariable(calendarOfflineAttachmentPayload);
            c18531.L$4 = strOfflineAttachmentIdToCacheKey;
            c18531.label = 1;
            if (calendarAttachmentRepository.updateEtag(strOfflineAttachmentIdToCacheKey, strTrim, c18531) == coroutine_suspended) {
                return coroutine_suspended;
            }
            str = strTrim;
            str2 = strOfflineAttachmentIdToCacheKey;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) c18531.L$4;
            str = (String) c18531.L$2;
            ResultKt.throwOnFailure(obj);
        }
        Logger.d$default(this.log, "Saved ETag for " + str2 + ": " + str, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileDownloadState startDownload$lambda$0(DownloadObjectParams downloadObjectParams, FileDownloadState fileDownloadState, FileDownloadState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new FileDownloadError(downloadObjectParams, fileDownloadState.getDownloadedLength(), new IllegalArgumentException("Invalid payload type"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileDownloadState startDownload$lambda$1(DownloadObjectParams downloadObjectParams, FileDownloadState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new FileDownloaded(downloadObjectParams, downloadObjectParams.getFileLength(), downloadObjectParams.getDestinationPath(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileDownloadState startDownload$lambda$2(DownloadObjectParams downloadObjectParams, FileDownloadState fileDownloadState, FileDownloadState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new FileDownloadInProgress(downloadObjectParams, fileDownloadState.getDownloadedLength());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileDownloadState startDownload$lambda$4(DownloadObjectParams downloadObjectParams, long j10, String str, FileDownloadState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new FileDownloaded(downloadObjectParams, j10, str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileDownloadState startDownload$lambda$6(DownloadObjectParams downloadObjectParams, long j10, Throwable th2, FileDownloadState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Exception iOException = th2 instanceof Exception ? (Exception) th2 : null;
        if (iOException == null) {
            iOException = new IOException("Download failed");
        }
        return new FileDownloadError(downloadObjectParams, j10, iOException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object writeChunkToFile(ResponseBody responseBody, File file, boolean z10, Continuation<? super Long> continuation) {
        InputStream inputStreamByteStream = responseBody.byteStream();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, z10);
            try {
                byte[] bArr = new byte[8192];
                long j10 = 0;
                while (true) {
                    int i10 = inputStreamByteStream.read(bArr);
                    if (i10 == -1) {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, null);
                        CloseableKt.closeFinally(inputStreamByteStream, null);
                        return Boxing.boxLong(j10);
                    }
                    if (!JobKt.isActive(continuation.getContext())) {
                        throw new IOException("Download cancelled");
                    }
                    fileOutputStream.write(bArr, 0, i10);
                    fileOutputStream.getFD().sync();
                    j10 += (long) i10;
                }
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(fileOutputStream, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            try {
                throw th4;
            } catch (Throwable th5) {
                CloseableKt.closeFinally(inputStreamByteStream, th4);
                throw th5;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x03ad  */
    /* JADX WARN: Code duplicated, block: B:104:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:110:0x0431  */
    /* JADX WARN: Code duplicated, block: B:112:0x0444  */
    /* JADX WARN: Code duplicated, block: B:113:0x0449  */
    /* JADX WARN: Code duplicated, block: B:117:0x0489  */
    /* JADX WARN: Code duplicated, block: B:43:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:45:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:46:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:57:0x0227  */
    /* JADX WARN: Code duplicated, block: B:60:0x023b  */
    /* JADX WARN: Code duplicated, block: B:65:0x028a  */
    /* JADX WARN: Code duplicated, block: B:66:0x02a4  */
    /* JADX WARN: Code duplicated, block: B:70:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:73:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:74:0x02db  */
    /* JADX WARN: Code duplicated, block: B:76:0x02df  */
    /* JADX WARN: Code duplicated, block: B:88:0x0360  */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    /* JADX WARN: Code duplicated, block: B:97:0x0399  */
    /* JADX WARN: Code duplicated, block: B:99:0x03a8  */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x042b, code lost:
    
        if (processDownloadedFile(r9, r2, r11) == r12) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x01a0, code lost:
    
        if (r0.update(r5, r11) == r12) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0283, code lost:
    
        if (r13.update(r5, r11) == r12) goto L116;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:43:0x01d0, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:60:0x023b, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:65:0x028a, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v57, types: [int] */
    /* JADX WARN: Type inference failed for: r0v69, types: [int] */
    /* JADX WARN: Type inference failed for: r0v93 */
    /* JADX WARN: Type inference failed for: r0v94 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r18v0, types: [ru.mail.calendar.data.network.download.CalendarAttachmentDownloader] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v26, types: [int] */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.StringBuilder] */
    @Override // vk.team.cloud.download.api.download.Downloader
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object startDownload(@org.jetbrains.annotations.NotNull vk.team.cloud.download.api.download.UpdateDownloadInterface r19, @org.jetbrains.annotations.NotNull vk.team.cloud.download.api.progress.observe.FileDownloadState r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instruction units count: 1192
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ru.mail.calendar.data.network.download.CalendarAttachmentDownloader.startDownload(vk.team.cloud.download.api.download.UpdateDownloadInterface, vk.team.cloud.download.api.progress.observe.FileDownloadState, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
